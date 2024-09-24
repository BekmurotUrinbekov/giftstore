package uz.giftstore.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.giftstore.dto.res.ResBasketDto;
import uz.giftstore.dto.res.ResProductDto;
import uz.giftstore.dto.res.ResReactionDto;
import uz.giftstore.entity.*;
import uz.giftstore.entity.Basket;
import uz.giftstore.repository.BasketRepository;
import uz.giftstore.repository.ProductRepository;
import uz.giftstore.repository.SessionEntityRepository;
import uz.giftstore.repository.UserRepository;
import uz.giftstore.service.BasketService;
import uz.giftstore.service.mapper.BasketMapper;
import uz.giftstore.service.mapper.ProductMapper;
import uz.giftstore.service.mapper.ReactionMapper;
import uz.giftstore.utils.JwtTokenUteils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final JwtTokenUteils jwtTokenUteils;
    private final SessionEntityRepository sessionEntityRepository;
    @Override
    public ResponseEntity<ResBasketDto> createBasket(Long productId, HttpServletRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        String authHeader = request.getHeader("Authorization");
        String username = null;
        User user = null;
        String sessionId = request.getSession(true).getId(); // Ensure session is created if it doesn't exist
        SessionEntity sessionEntity = sessionEntityRepository.findBySessionId(sessionId)
                .orElseGet(() -> {
                    SessionEntity newSession = new SessionEntity();
                    newSession.setSessionId(sessionId);
                    return sessionEntityRepository.save(newSession);
                });

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            username = jwtTokenUteils.getUsername(jwt);
            user = userRepository.findByUserName(username)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
        }

        // If user is authenticated
        if (user != null) {
            List<Basket> userBaskets = basketRepository.findBySessionEntity_User_Id(user.getId());
            Optional<Basket> existingBasket = userBaskets.stream()
                    .filter(b -> b.getSessionEntity().getSessionId().equals(sessionEntity.getSessionId()))
                    .findAny();

            if (existingBasket.isPresent()) {
                Basket basket = existingBasket.get();
                if (!basket.getIsBasket()) {
                    basket.setIsBasket(true);
                    basket.setDeleted(false);
                    basketRepository.save(basket);
                }
                return ResponseEntity.ok(BasketMapper.basketToResBasketDto(basket));
            } else {
                return createNewBasket(sessionEntity, product);
            }
        } else {
            // For guest users
            Optional<Basket> checkIsBasket = basketRepository.findBySessionEntityAndProduct(sessionEntity, product);
            if (checkIsBasket.isPresent()) {
                Basket basket = checkIsBasket.get();
                if (!basket.getIsBasket()) {
                    basket.setIsBasket(true);
                    basket.setDeleted(false);
                    basketRepository.save(basket);
                }
                return ResponseEntity.ok(BasketMapper.basketToResBasketDto(basket));
            } else {
                return createNewBasket(sessionEntity, product);
            }
        }
    }

    private ResponseEntity<ResBasketDto> createNewBasket(SessionEntity sessionEntity, Product product) {
        Basket basket = new Basket();
        basket.setSessionEntity(sessionEntity);
        basket.setProduct(product);
        basket.setIsBasket(true);
        basketRepository.save(basket);

        sessionEntity.addBasket(basket);
        sessionEntityRepository.save(sessionEntity);

        product.addBasket(basket);
        productRepository.save(product);

        return ResponseEntity.ok(BasketMapper.basketToResBasketDto(basket));
    }

    //    @Override
//    public ResponseEntity<ResBasketDto> createBasket(Long productId, Long userId ) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
//        Optional<Basket> checkIsBasket = basketRepository.findByUserAndProduct(user, product);
//        Basket basket=null;
//        if (checkIsBasket.isPresent()) {
//            basket = checkIsBasket.get();
//        }
//        if (!checkIsBasket.isPresent()) {
//            Basket basket2 = new Basket();
//            basket2.setUser(user);
//            basket2.setProduct(product);
//            basket2.setIsBasket(true);
//            basketRepository.save(basket2);
//            user.addBasket(basket2);
//            userRepository.save(user);
//            product.addBasket(basket2);
//            productRepository.save(product);
//            basketRepository.save(basket2);
//            return ResponseEntity.ok(BasketMapper.basketToResBasketDto(basket2));
//        }else if (checkIsBasket.isPresent() && checkIsBasket.get().getIsBasket() == false) {
//            basket.setDeleted(false);
//            basket.setIsBasket(true);
//            basketRepository.save(basket);
//        }
//        return ResponseEntity.ok(BasketMapper.basketToResBasketDto(basket));
//    }
    @Override
    public ResponseEntity<List<ResReactionDto>> findMostBasketProducts(Integer days, Integer page, Integer size) {
        LocalDateTime localDateTime=LocalDateTime.now().minusDays(days);
        List<Object[]> mostBasketProductsOfWeek = basketRepository.findMostBasketProducts(localDateTime,PageRequest.of(page, size));
        return ResponseEntity.ok(mostBasketProductsOfWeek.stream()
                .map(row -> {
                    Product product = (Product) row[0];
                    Long amountReaction = (Long) row[1];
                    ResReactionDto resReactionDto = ReactionMapper.reactionToResReactionDto(product, amountReaction);
                    return resReactionDto;
                })
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<ResProductDto>> getUserBasketProducts(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        User user = null;

        // Ensure session is created if it doesn't exist
        String sessionId = request.getSession(true).getId();
        SessionEntity sessionEntity = sessionEntityRepository.findBySessionId(sessionId)
                .orElseGet(() -> {
                    SessionEntity newSession = new SessionEntity();
                    newSession.setSessionId(sessionId);
                    return sessionEntityRepository.save(newSession);
                });

        // Check if user is authenticated
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            username = jwtTokenUteils.getUsername(jwt);
            user = userRepository.findByUserName(username)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
        }

        List<Basket> baskets;
        if (user != null) {
            // Retrieve baskets by user ID
            baskets = basketRepository.findBySessionEntity_User_Id(user.getId());
        } else {
            // Retrieve baskets by session entity
            baskets = basketRepository.findBasketBySessionEntity(sessionEntity);
        }

        // Map the baskets to response DTOs
        List<ResProductDto> basketDtos = baskets.stream()
                .map(Basket::getProduct)
                .map(ProductMapper::productToResProductDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(basketDtos);
    }

    @Override
    public ResponseEntity<List<ResReactionDto>> getTopBasketProducts(Integer page, Integer size) {
        List<Object[]> result = basketRepository.findMostBaskedProducts(PageRequest.of(page, size));
        return ResponseEntity.ok(result.stream()
                .map(row -> {
                    Product product = (Product) row[0];
                    Long amountReaction = (Long) row[1];
                    ResReactionDto resReactionDto = ReactionMapper.reactionToResReactionDto(product, amountReaction);
                    return resReactionDto;
                })
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Boolean> deleteBasket(Long basketId) {
        Basket basketEntity = getBasketEntity(basketId);
        basketEntity.setDeleted(true);
        basketEntity.setIsBasket(false);
        basketRepository.save(basketEntity);
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<ResBasketDto> getBasket(Long basketId) {
        return ResponseEntity.ok(BasketMapper.basketToResBasketDto(getBasketEntity(basketId)));
    }

    @Override
    public Basket getBasketEntity(Long basketId) {
        Optional<Basket> basketEntity = findBasketEntity(basketId);
        if (basketEntity.isPresent()){
            return basketEntity.get();
        }
        throw new EntityNotFoundException("basket entity not found");
    }

    @Override
    public Optional<Basket> findBasketEntity(Long basketId) {
        return basketRepository.findById(basketId);
    }

    @Override
    public ResponseEntity<Page<ResBasketDto>> getAll() {
        return ResponseEntity.ok(getAllEntity().map(BasketMapper::basketToResBasketDto));
    }

    @Override
    public Page<Basket> getAllEntity() {
        return basketRepository.findAll(PageRequest.of(0,20));
    }
}

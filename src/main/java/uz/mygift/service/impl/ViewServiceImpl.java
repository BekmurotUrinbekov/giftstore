package uz.mygift.service.impl;


import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.mygift.dto.res.ResViewDto;
import uz.mygift.dto.res.ResProductDto;
import uz.mygift.dto.res.ResReactionDto;
import uz.mygift.dto.res.ResViewDto;
import uz.mygift.entity.*;
import uz.mygift.entity.Views;
import uz.mygift.repository.ProductRepository;
import uz.mygift.repository.SessionEntityRepository;
import uz.mygift.repository.UserRepository;
import uz.mygift.repository.ViewsRepository;
import uz.mygift.service.ViewService;
import uz.mygift.service.mapper.ViewMapper;
import uz.mygift.service.mapper.ProductMapper;
import uz.mygift.service.mapper.ReactionMapper;
import uz.mygift.service.mapper.ViewMapper;
import uz.mygift.utils.JwtTokenUteils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViewServiceImpl implements ViewService {
    private final ViewsRepository viewsRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final SessionEntityRepository sessionEntityRepository;
    private final JwtTokenUteils jwtTokenUteils;

    @Override
    public ResponseEntity<ResViewDto> createView(Long productId, HttpServletRequest request) {
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
            List<Views> userViews = viewsRepository.findBySessionEntity_User_Id(user.getId());
            Optional<Views> existingView = userViews.stream()
                    .filter(b -> b.getSessionEntity().getSessionId().equals(sessionEntity.getSessionId()))
                    .findAny();

            if (existingView.isPresent()) {
                Views view = existingView.get();
                if (!view.getIsViewed()) {
                    view.setIsViewed(true);
                    view.setDeleted(false);
                    viewsRepository.save(view);
                }
                return ResponseEntity.ok(ViewMapper.viewToResViewDto(view));
            } else {
                return createNewView(sessionEntity, product);
            }
        } else {
            // For guest users
            Optional<Views> checkIsView = viewsRepository.findBySessionEntityAndProduct(sessionEntity, product);
            if (checkIsView.isPresent()) {
                Views view = checkIsView.get();
                if (!view.getIsViewed()) {
                    view.setIsViewed(true);
                    view.setDeleted(false);
                    viewsRepository.save(view);
                }
                return ResponseEntity.ok(ViewMapper.viewToResViewDto(view));
            } else {
                return createNewView(sessionEntity, product);
            }
        }
    }

    private ResponseEntity<ResViewDto> createNewView(SessionEntity sessionEntity, Product product) {
        Views view = new Views();
        view.setSessionEntity(sessionEntity);
        view.setProduct(product);
        view.setIsViewed(true);
        viewsRepository.save(view);

        sessionEntity.addViews(view);
        sessionEntityRepository.save(sessionEntity);

        product.addViews(view);
        productRepository.save(product);

        return ResponseEntity.ok(ViewMapper.viewToResViewDto(view));
    }
    @Override
    public ResponseEntity<List<ResReactionDto>> findMostViewsProducts(Integer days, Integer page, Integer size) {
        LocalDateTime localDateTime=LocalDateTime.now().minusDays(days);
        List<Object[]> mostViewsProductsOfWeek = viewsRepository.findMostViewsProducts(localDateTime,PageRequest.of(page, size));
        return ResponseEntity.ok(mostViewsProductsOfWeek.stream()
                .map(row -> {
                    Product product = (Product) row[0];
                    Long amountReaction = (Long) row[1];
                    ResReactionDto resReactionDto = ReactionMapper.reactionToResReactionDto(product, amountReaction);
                    return resReactionDto;
                })
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<ResProductDto>> getUserViewsProducts(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("user entity not found"));
        List<Views> views = viewsRepository.findBySessionEntity_User_Id(userId);
        return ResponseEntity.ok(views.stream()
                .map(Views::getProduct)
                .map(ProductMapper::productToResProductDto).collect(Collectors.toList()));
    }
    @Override
    public ResponseEntity<List<ResReactionDto>> getTopViewsProducts(Integer page, Integer size) {
        List<Object[]> result = viewsRepository.findMostViewedProducts(PageRequest.of(page, size));
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
    public ResponseEntity<Long> countViewByProductId(Long productId) {
        productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        Optional<Long> countViews = viewsRepository.countViewsByProductId(productId);
        if (countViews.isPresent()) {
            return ResponseEntity.ok(countViews.get());
        }
        return ResponseEntity.ok(0L);
    }


//    @Override
//    public ResponseEntity<Boolean> increaseView(Long viewId) {
//        Views viewEntity = getViewEntity(viewId);
//        viewEntity.setViews(viewEntity.getViews()+1);
//        viewsRepository.save(viewEntity);
//        return ResponseEntity.ok(true);
//    }
//
//    @Override
//    public ResponseEntity<Boolean> decreaseView(Long viewId) {
//        Views viewEntity = getViewEntity(viewId);
//        viewEntity.setViews(viewEntity.getViews()-1);
//        viewsRepository.save(viewEntity);
//        return ResponseEntity.ok(true);
//    }

    @Override
    public ResponseEntity<Boolean> deleteView(Long viewId) {
        Views viewEntity = getViewEntity(viewId);
        viewEntity.setDeleted(true);
        viewEntity.setIsViewed(false);
        viewsRepository.save(viewEntity);
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<ResViewDto> getView(Long viewId) {
        return ResponseEntity.ok(ViewMapper.viewToResViewDto(getViewEntity(viewId)));
    }

    @Override
    public Views getViewEntity(Long viewId) {
        Optional<Views> viewEntity = findViewEntity(viewId);
        if (viewEntity.isPresent()){
            return viewEntity.get();
        }
        throw new EntityNotFoundException("view entity not found");
    }

    @Override
    public Optional<Views> findViewEntity(Long viewId) {
        return viewsRepository.findById(viewId);
    }

    @Override
    public ResponseEntity<Page<ResViewDto>> getAll() {
        return ResponseEntity.ok(getAllEntity().map(ViewMapper::viewToResViewDto));
    }

    @Override
    public Page<Views> getAllEntity() {
        return viewsRepository.findAll(PageRequest.of(0,10));
    }
}

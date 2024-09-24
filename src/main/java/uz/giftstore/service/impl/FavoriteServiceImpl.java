package uz.giftstore.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.giftstore.dto.res.ResFavoriteDto;
import uz.giftstore.dto.res.ResProductDto;
import uz.giftstore.dto.res.ResReactionDto;
import uz.giftstore.entity.*;
import uz.giftstore.repository.FavoriteRepository;
import uz.giftstore.repository.ProductRepository;
import uz.giftstore.repository.SessionEntityRepository;
import uz.giftstore.repository.UserRepository;
import uz.giftstore.service.FavoriteService;
import uz.giftstore.service.mapper.FavoriteMapper;
import uz.giftstore.service.mapper.ProductMapper;
import uz.giftstore.service.mapper.ReactionMapper;
import uz.giftstore.utils.JwtTokenUteils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final SessionEntityRepository sessionEntityRepository;
    private final JwtTokenUteils jwtTokenUteils;
    @Override
    public ResponseEntity<ResFavoriteDto> createFavorite(Long productId, HttpServletRequest request) {
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
            List<Favorite> userFavorites = favoriteRepository.findBySessionEntity_User_Id(user.getId());
            Optional<Favorite> existingFavorite = userFavorites.stream()
                    .filter(b -> b.getSessionEntity().getSessionId().equals(sessionEntity.getSessionId()))
                    .findAny();

            if (existingFavorite.isPresent()) {
                Favorite Favorite = existingFavorite.get();
                if (!Favorite.getIsFavorite()) {
                    Favorite.setIsFavorite(true);
                    Favorite.setDeleted(false);
                    favoriteRepository.save(Favorite);
                }
                return ResponseEntity.ok(FavoriteMapper.favoriteToResFavoriteDto(Favorite));
            } else {
                return createNewFavorite(sessionEntity, product);
            }
        } else {
            // For guest users
            Optional<Favorite> checkIsFavorite = favoriteRepository.findBySessionEntityAndProduct(sessionEntity, product);
            if (checkIsFavorite.isPresent()) {
                Favorite Favorite = checkIsFavorite.get();
                if (!Favorite.getIsFavorite()) {
                    Favorite.setIsFavorite(true);
                    Favorite.setDeleted(false);
                    favoriteRepository.save(Favorite);
                }
                return ResponseEntity.ok(FavoriteMapper.favoriteToResFavoriteDto(Favorite));
            } else {
                return createNewFavorite(sessionEntity, product);
            }
        }
    }

    private ResponseEntity<ResFavoriteDto> createNewFavorite(SessionEntity sessionEntity, Product product) {
        Favorite Favorite = new Favorite();
        Favorite.setSessionEntity(sessionEntity);
        Favorite.setProduct(product);
        Favorite.setIsFavorite(true);
        favoriteRepository.save(Favorite);

        sessionEntity.addFavorite(Favorite);
        sessionEntityRepository.save(sessionEntity);

        product.addFavorite(Favorite);
        productRepository.save(product);

        return ResponseEntity.ok(FavoriteMapper.favoriteToResFavoriteDto(Favorite));
    }
    @Override
    public ResponseEntity<List<ResReactionDto>> findMostFavoriteProducts(Integer days,Integer page, Integer size) {
        LocalDateTime localDateTime=LocalDateTime.now().minusDays(days);
        List<Object[]> mostFavoriteProductsOfWeek = favoriteRepository.findMostFavoriteProducts(localDateTime,PageRequest.of(page, size));
        return ResponseEntity.ok(mostFavoriteProductsOfWeek.stream()
                .map(row -> {
                    Product product = (Product) row[0];
                    Long amountReaction = (Long) row[1];
                    ResReactionDto resReactionDto = ReactionMapper.reactionToResReactionDto(product, amountReaction);
                    return resReactionDto;
                })
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<ResProductDto>> getUserFavoriteProducts(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("user entity not found"));
        List<Favorite> favorites = favoriteRepository.findBySessionEntity_User_Id(userId);
        return ResponseEntity.ok(favorites.stream()
                .map(Favorite::getProduct)
                .map(ProductMapper::productToResProductDto).collect(Collectors.toList()));
    }
    @Override
    public ResponseEntity<List<ResReactionDto>> getTopFavoritedProducts(Integer page, Integer size) {
        List<Object[]> result = favoriteRepository.findMostFavoritedProducts(PageRequest.of(page, size));
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
    public ResponseEntity<Boolean> deleteFavorite(Long favoriteId) {
        Favorite favoriteEntity = getFavoriteEntity(favoriteId);
        favoriteEntity.setDeleted(true);
        favoriteEntity.setIsFavorite(false);
        favoriteRepository.save(favoriteEntity);
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<ResFavoriteDto> getFavorite(Long favoriteId) {
        return ResponseEntity.ok(FavoriteMapper.favoriteToResFavoriteDto(getFavoriteEntity(favoriteId)));
    }

    @Override
    public Favorite getFavoriteEntity(Long favoriteId) {
        Optional<Favorite> favoriteEntity = findFavoriteEntity(favoriteId);
        if (favoriteEntity.isPresent()){
            return favoriteEntity.get();
        }
        throw new EntityNotFoundException("favorite entity not found");
    }

    @Override
    public Optional<Favorite> findFavoriteEntity(Long favoriteId) {
        return favoriteRepository.findById(favoriteId);
    }

    @Override
    public ResponseEntity<Page<ResFavoriteDto>> getAll() {
        return ResponseEntity.ok(getAllEntity().map(FavoriteMapper::favoriteToResFavoriteDto));
    }

    @Override
    public Page<Favorite> getAllEntity() {
        return favoriteRepository.findAll(PageRequest.of(0,20));
    }
}

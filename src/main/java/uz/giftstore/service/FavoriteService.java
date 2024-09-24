package uz.giftstore.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.giftstore.dto.res.ResFavoriteDto;
import uz.giftstore.dto.res.ResProductDto;
import uz.giftstore.dto.res.ResReactionDto;
import uz.giftstore.entity.Favorite;

import java.util.List;
import java.util.Optional;


public interface FavoriteService {

    ResponseEntity<ResFavoriteDto> createFavorite(Long productId, HttpServletRequest request);

    ResponseEntity<Boolean> deleteFavorite(Long favoriteId);

    ResponseEntity<ResFavoriteDto> getFavorite(Long favoriteId);
    ResponseEntity<List<ResProductDto>> getUserFavoriteProducts(Long userId);
    ResponseEntity<List<ResReactionDto>> getTopFavoritedProducts(Integer page, Integer size);
    ResponseEntity<List<ResReactionDto>> findMostFavoriteProducts(Integer days,Integer page, Integer size);
    Favorite getFavoriteEntity(Long favoriteId);

    Optional<Favorite> findFavoriteEntity(Long favoriteId);


    ResponseEntity<Page<ResFavoriteDto>> getAll();

    Page<Favorite> getAllEntity();



}

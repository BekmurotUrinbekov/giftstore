package uz.mygift.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.mygift.dto.res.ResFavoriteDto;
import uz.mygift.dto.res.ResProductDto;
import uz.mygift.dto.res.ResReactionDto;
import uz.mygift.entity.Favorite;
import uz.mygift.entity.User;

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

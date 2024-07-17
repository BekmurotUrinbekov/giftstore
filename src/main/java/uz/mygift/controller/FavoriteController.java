package uz.mygift.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mygift.base.BaseUrl;
import uz.mygift.dto.res.ResFavoriteDto;
import uz.mygift.dto.res.ResProductDto;
import uz.mygift.dto.res.ResReactionDto;
import uz.mygift.service.impl.FavoriteServiceImpl;

import java.util.List;

@RestController
@RequestMapping(BaseUrl.FAVORITE)
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteServiceImpl favoriteService;

    @GetMapping(BaseUrl.GET_ALL)
    public ResponseEntity<Page<ResFavoriteDto>> getAll(){
        return favoriteService.getAll();
    }

    @GetMapping(BaseUrl.DELETE_BY_ID)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
        return favoriteService.deleteFavorite(id);
    }

    @GetMapping(BaseUrl.CREATE_WHIT_PRODUCT_ID)
    public ResponseEntity<ResFavoriteDto> createFavorite(@PathVariable("productId") Long productId, HttpServletRequest request){
        return favoriteService.createFavorite(productId, request);
    }
    @GetMapping(BaseUrl.REACTION_PRODUCTS_WITH_USER_ID)
    public ResponseEntity<List<ResProductDto>> getUserFavoriteProducts(@PathVariable("userId") Long userId){
        return favoriteService.getUserFavoriteProducts(userId);
    }
    @GetMapping(BaseUrl.REACTION_ALL_TIME)
    public ResponseEntity<List<ResReactionDto>> getTopFavoritedProducts(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        return favoriteService.getTopFavoritedProducts(page,size);
    }
    @GetMapping(BaseUrl.REACTION_WEEK)
    public ResponseEntity<List<ResReactionDto>> findMostFavoriteProductsOfWeek(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        return favoriteService.findMostFavoriteProducts(7,page,size);
    }
    @GetMapping(BaseUrl.REACTION_MONTH)
    public ResponseEntity<List<ResReactionDto>> findMostFavoriteProductsOfMonth(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        return favoriteService.findMostFavoriteProducts(30,page,size);
    }
    @GetMapping(BaseUrl.REACTION_YEAR)
    public ResponseEntity<List<ResReactionDto>> findMostFavoriteProductsOfYear(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        return favoriteService.findMostFavoriteProducts(365,page,size);
    }
    @GetMapping(BaseUrl.FIND_BY_ID)
    public ResponseEntity<ResFavoriteDto> findById(@PathVariable("id") Long id){
        return favoriteService.getFavorite(id);
    }


}

package uz.giftstore.service.mapper;

import uz.giftstore.dto.res.ResFavoriteDto;
import uz.giftstore.entity.Favorite;
public class FavoriteMapper {
    public static ResFavoriteDto favoriteToResFavoriteDto(Favorite favorite) {
               ResFavoriteDto resFavoriteDto = new ResFavoriteDto();
        resFavoriteDto.setIsFavorite(favorite.getIsFavorite());
        resFavoriteDto.setProduct(ProductMapper.productToResProductDto(favorite.getProduct()));

        return resFavoriteDto;
    }}

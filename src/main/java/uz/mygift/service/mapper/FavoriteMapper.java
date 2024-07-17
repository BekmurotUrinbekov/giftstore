package uz.mygift.service.mapper;

import uz.mygift.dto.res.ResFavoriteDto;
import uz.mygift.entity.Favorite;
public class FavoriteMapper {
    public static ResFavoriteDto favoriteToResFavoriteDto(Favorite favorite) {
               ResFavoriteDto resFavoriteDto = new ResFavoriteDto();
        resFavoriteDto.setIsFavorite(favorite.getIsFavorite());
        resFavoriteDto.setProduct(ProductMapper.productToResProductDto(favorite.getProduct()));

        return resFavoriteDto;
    }}

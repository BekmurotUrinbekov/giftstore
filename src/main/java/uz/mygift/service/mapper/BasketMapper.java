package uz.mygift.service.mapper;

import uz.mygift.dto.res.ResBasketDto;
import uz.mygift.entity.Basket;

public class BasketMapper {
    public static ResBasketDto basketToResBasketDto(Basket basket) {
        ResBasketDto resBasketDto = new ResBasketDto();
        resBasketDto.setIsBasket(basket.getIsBasket());
        resBasketDto.setProduct(ProductMapper.productToResProductDto(basket.getProduct()));

        return resBasketDto;
    }}

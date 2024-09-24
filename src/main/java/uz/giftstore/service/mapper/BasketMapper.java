package uz.giftstore.service.mapper;

import uz.giftstore.dto.res.ResBasketDto;
import uz.giftstore.entity.Basket;

public class BasketMapper {
    public static ResBasketDto basketToResBasketDto(Basket basket) {
        ResBasketDto resBasketDto = new ResBasketDto();
        resBasketDto.setIsBasket(basket.getIsBasket());
        resBasketDto.setProduct(ProductMapper.productToResProductDto(basket.getProduct()));

        return resBasketDto;
    }}

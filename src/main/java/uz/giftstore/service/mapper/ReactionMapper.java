package uz.giftstore.service.mapper;

import uz.giftstore.dto.res.ResProductDto;
import uz.giftstore.dto.res.ResReactionDto;
import uz.giftstore.entity.Product;

public class ReactionMapper {
    public static ResReactionDto reactionToResReactionDto(Product product, Long amountReaction) {
        ResProductDto resProductDto = ProductMapper.productToResProductDto(product);
        ResReactionDto resReactionDto = new ResReactionDto();
        resReactionDto.setAmountReaction(amountReaction);
        resReactionDto.setProduct(resProductDto);
        return resReactionDto;
    }}

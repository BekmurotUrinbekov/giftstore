package uz.mygift.service.mapper;

import uz.mygift.dto.res.ResProductDto;
import uz.mygift.dto.res.ResReactionDto;
import uz.mygift.entity.Product;

public class ReactionMapper {
    public static ResReactionDto reactionToResReactionDto(Product product, Long amountReaction) {
        ResProductDto resProductDto = ProductMapper.productToResProductDto(product);
        ResReactionDto resReactionDto = new ResReactionDto();
        resReactionDto.setAmountReaction(amountReaction);
        resReactionDto.setProduct(resProductDto);
        return resReactionDto;
    }}

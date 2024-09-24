package uz.giftstore.service.mapper;

import uz.giftstore.dto.res.ResImageDto;
import uz.giftstore.entity.Image;
public class ImageMapper {

    public static ResImageDto imageToResImageDto(Image image) {
        ResImageDto resImageDto = new ResImageDto();
        resImageDto.setName(image.getName());
        resImageDto.setContentType(image.getContentType());
        resImageDto.setSize(image.getSize());
        resImageDto.setType(image.getType());
        return resImageDto;
    }
}

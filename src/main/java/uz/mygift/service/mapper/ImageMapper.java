package uz.mygift.service.mapper;

import uz.mygift.dto.res.ResImageDto;
import uz.mygift.entity.Image;
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

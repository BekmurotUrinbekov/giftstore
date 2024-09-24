package uz.giftstore.service.mapper;

import uz.giftstore.dto.res.ResViewDto;
import uz.giftstore.entity.Views;
public class ViewMapper {
    public static ResViewDto viewToResViewDto(Views views) {
        if (views == null) {
            return null;
        }

        ResViewDto resViewDto = new ResViewDto();
        resViewDto.setViews(views.getViews());
//        resViewDto.setProduct(ProductToResProductDto(views.getProduct()));

        return resViewDto;
    }
}

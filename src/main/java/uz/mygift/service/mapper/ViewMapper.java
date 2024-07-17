package uz.mygift.service.mapper;

import uz.mygift.dto.res.ResViewDto;
import uz.mygift.entity.Views;
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

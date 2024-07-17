package uz.mygift.service.mapper;

import uz.mygift.dto.req.ReqCategoryDto;
import uz.mygift.dto.res.ResCategoryDto;
import uz.mygift.entity.Category;

public class CategoryMapper {
public static ResCategoryDto categoryToResCategoryDto(Category category) {
    if (category == null) {
        return null;
    }
    ResCategoryDto resCategoryDto = new ResCategoryDto();
    resCategoryDto.setCategoryName(category.getCategoryName());
//    if (category.getProducts() != null) {
//        List<ResProductDto> productDtos = category.getProducts().stream()
//                .map(this::convertToResProductDto)
//                .collect(Collectors.toList());
//        resCategoryDto.setProducts(productDtos);
//    }
    return resCategoryDto;
}

    // Method to convert ReqCategoryDto to Category entity
    public static Category reqCategoryDtoToCategory(ReqCategoryDto reqCategoryDto) {
        if (reqCategoryDto == null) {
            return null;
        }

        Category category = new Category();
        category.setCategoryName(reqCategoryDto.getCategoryName());
        return category;
    }

}

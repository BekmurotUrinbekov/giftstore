package uz.mygift.dto.res;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ResProductDto {

    String productName;
    String fullName;
    String article;
    Double productPrice;
    String productDescription;

    String gender;
    ResProducDto productType;
    ResDiscountDto discount;
    ResCategoryDto category;
    ResImageDto image;
    ResBasketDto basket;
    ResFavoriteDto favorite;
    ResViewDto views;
}

package uz.giftstore.dto.upd;


import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;
import uz.giftstore.enums.Gender;
import uz.giftstore.validation.EnumValidator;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDto {
    String productName;
    String fullName;
    String article;
    Double productPrice;
    String productDescription;
    @EnumValidator(enumClazz = Gender.class)
    @Nullable
    String gender;
    @Positive
    @Nullable
    Long productType_id;
    @Positive
    @Nullable
    Long discount_id;
    @Positive
    @Nullable
    Long category_id;
    @Positive
    @Nullable
    Long image_id;
    @Positive
    @Nullable
    Long basket_id;
    @Positive
    @Nullable
    Long favorite_id;
    @Positive
    @Nullable
    Long views_id;
}

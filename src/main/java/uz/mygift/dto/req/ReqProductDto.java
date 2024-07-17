package uz.mygift.dto.req;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.mygift.enums.Gender;
import uz.mygift.validation.EnumValidator;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ReqProductDto {
    @NotNull
    @NotBlank
    String productName;
    @NotNull
    @NotBlank
    String fullName;
    @Positive
    @NotNull
    Double productPrice;
    @NotNull
    @NotBlank
    String productDescription;
    @EnumValidator(enumClazz = Gender.class)
    String gender;
    @NotNull
    @Positive
    Long productType_id;
//    @NotNull
    @Nullable
    @Positive
    Long discount_id;
    @NotNull
    @Positive
    Long category_id;
    @NotNull
    @Positive
    Long image_id;

}

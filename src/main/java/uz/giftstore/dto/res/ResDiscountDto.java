package uz.giftstore.dto.res;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ResDiscountDto {
    Double discountPrice;
    LocalDate deadline;
    ResImageDto imageDto;
    ResProductDto productDto;
}

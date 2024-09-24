package uz.giftstore.dto.upd;


import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDiscountDto {
    @PositiveOrZero
    Double discountPrice;
    @FutureOrPresent
    @Nullable
    LocalDate deadline;
    @Positive
    @Nullable
    Long image_id;
    @Positive
    @Nullable
    Long product_id;
}

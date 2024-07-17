package uz.mygift.dto.req;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class ReqDiscountDto {
    @NotNull
    @Positive
    Double discountPrice;
    @FutureOrPresent
    LocalDate deadline;
    Long image_id;
    @NotNull
    @Positive
    Long product_id;
}

package uz.mygift.dto.req;


import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
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
public class ReqServiceDto {
    @NotNull
    @NotBlank
    String servicesName;
    @Positive
    @NotNull
    Double servicePrice;
    @Positive
    Integer fromProductAmount;
    @Positive
    Integer toProductAmount;
    String serviceDescription;
    @Positive
    @NotNull
    Long servicesType_id;
}

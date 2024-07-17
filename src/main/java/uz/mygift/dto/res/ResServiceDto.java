package uz.mygift.dto.res;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ResServiceDto {

    String servicesName;
    Double servicePrice;
    Integer fromProductAmount;
    Integer toProductAmount;
    String serviceDescription;
    ResServiceTypeDto serviceTypeDto;
    List<ResImageDto> resImageDtos;
}

package uz.giftstore.dto.res;


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

package uz.giftstore.dto.upd;

import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateServiceDto {
    String servicesName;
    @Positive
    @Nullable
    Double servicePrice;
    @Positive
    @Nullable
    Integer fromProductAmount;
    @Positive
    @Nullable
    Integer toProductAmount;
    String serviceDescription;
    @Positive
    @Nullable
    Long servicesType_id;
}

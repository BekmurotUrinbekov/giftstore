package uz.mygift.dto.upd;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UpdatePartnerDto {
    @Nullable
    @NotBlank
    String partnerName;
    @Nullable
    @Min(1)
    Long aboutUs_id;
    @Nullable
    @Min(1)
    Long image_id;
}

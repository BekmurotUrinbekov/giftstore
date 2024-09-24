package uz.giftstore.dto.req;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ReqPartnerDto {
    @NotNull
    @NotBlank
    String partnerName;
    @NotNull
    @Min(1)
    Long aboutUs_id;
    @NotNull
    @NotBlank
    String image_id;
}

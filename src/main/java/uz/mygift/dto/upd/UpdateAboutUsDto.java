package uz.mygift.dto.upd;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class UpdateAboutUsDto {
    @Pattern(regexp = "^\\+998\\d{9}$", message = "Contact must follow the pattern +998XXXXXXXXX")
    @Nullable
    String contact;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @Nullable
    String email;
    @Pattern(regexp = "^https?://t\\.me/[a-zA-Z0-9_]{5,}$")
    @Nullable
    String telegram;
    @Nullable
    @Pattern(regexp = "^https?://(?:www\\.)?instagram\\.com/.+")
    String instagram;
    @Nullable
    String description;

}

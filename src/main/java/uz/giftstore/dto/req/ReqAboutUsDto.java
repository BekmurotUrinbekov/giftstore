package uz.giftstore.dto.req;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ReqAboutUsDto {
    @Pattern(regexp = "^\\+998\\d{9}$", message = "Contact must follow the pattern +998XXXXXXXXX")
    @NotNull(message = "Contact must not be null")
    @NotBlank(message = "Contact must not be blank")
    String contact;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    @NotNull(message = "Email must not be null")
    @NotBlank(message = "Email must not be blank")
    String email;

    @Pattern(regexp = "^https?://t\\.me/[a-zA-Z0-9_]{5,}$", message = "Invalid Telegram URL")
    @NotNull(message = "Telegram must not be null")
    @NotBlank(message = "Telegram must not be blank")
    String telegram;

    @Pattern(regexp = "^https?://(?:www\\.)?instagram\\.com/.+", message = "Invalid Instagram URL")
    @NotNull(message = "Instagram must not be null")
    @NotBlank(message = "Instagram must not be blank")
    String instagram;

    String description;

}


package uz.giftstore.dto.res;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ResAboutUsDto {

    String contact;
    String email;
    String telegram;
    String instagram;
    String description;

//    List<ResImageDto> images;

//    List<ResPartnerDto> partners;
}

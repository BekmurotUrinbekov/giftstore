package uz.mygift.dto.res;


import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.mygift.entity.Image;
import uz.mygift.entity.Partner;

import java.util.List;

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

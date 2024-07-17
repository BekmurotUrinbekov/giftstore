package uz.mygift.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;
import uz.mygift.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "about_us")
@SQLRestriction("deleted= false")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "images", "partners"})
public class AboutUs extends BaseEntity {
    String contact;
    String email;
    String telegram;
    String instagram;
    String description;

    @OneToMany(mappedBy = "aboutUs", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Image> images;

    @OneToMany(mappedBy = "aboutUs", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Partner> partners;

    public void addPartner(Partner partner) {
        if (partners == null) {
            partners = new ArrayList<>();
        }
        partners.add(partner);
    }

    public void addImage(Image image) {
        if (images == null) {
            images = new ArrayList<>();
        }
        images.add(image);
    }

    @Override
    public String toString() {
        return "AboutUs{" +
                "contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", telegram='" + telegram + '\'' +
                ", instagram='" + instagram + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

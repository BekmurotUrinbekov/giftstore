package uz.giftstore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import uz.giftstore.base.BaseEntity;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "partner")
@SQLRestriction("deleted= false")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "image", "aboutUs"})
public class Partner extends BaseEntity {
    String partnerName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "about_us_id")
    @JsonBackReference
    AboutUs aboutUs;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    Image image;

    @Override
    public String toString() {
        return "Partner{" +
                "partnerName='" + partnerName + '\'' +
                '}';
    }
}

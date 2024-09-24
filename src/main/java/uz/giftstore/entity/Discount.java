package uz.giftstore.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import uz.giftstore.base.BaseEntity;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discount")
@SQLRestriction("deleted= false")
public class Discount extends BaseEntity {
    Double discountPrice;
    LocalDate deadline;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    Boolean isDiscount;
    @OneToOne
    Image image;
    @OneToOne
    Product product;
}

package uz.mygift.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import uz.mygift.base.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

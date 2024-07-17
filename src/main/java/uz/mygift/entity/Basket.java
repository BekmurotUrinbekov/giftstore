package uz.mygift.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import uz.mygift.base.BaseEntity;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("deleted= false")
@Table(name = "basket")
public class Basket extends BaseEntity {
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    Boolean isBasket;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    @JsonBackReference
    SessionEntity sessionEntity;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    @JsonBackReference
//    User user;


}

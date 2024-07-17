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
@Table(name = "views")
@SQLRestriction("deleted= false")
public class Views extends BaseEntity {
    @Column(columnDefinition = "INTEGER DEFAULT 1")
    Integer views;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    Boolean isViewed;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    Product product;

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    @JsonBackReference
//    User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    @JsonBackReference
    SessionEntity sessionEntity;

}

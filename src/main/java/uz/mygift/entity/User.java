package uz.mygift.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import uz.mygift.base.BaseEntity;
import uz.mygift.enums.UserRole;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
@SQLRestriction("deleted= false")
public class User extends BaseEntity {
    @Column(unique = true)
    String userName;
    String password;

    @Enumerated(value = EnumType.STRING)
    UserRole userRole;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    List<SessionEntity> sessionEntities;


//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonManagedReference
//    List<Favorite> favorites;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonManagedReference
//    List<Basket> baskets;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonManagedReference
//    List<Views> views;

//    public  void addViews(Views view) {
//        if (views == null) {
//            views = new ArrayList<>();
//        }
//        views.add(view);
//    }
//    public  void addFavorite(Favorite favorite) {
//        if (favorites == null) {
//            favorites = new ArrayList<>();
//        }
//        favorites.add(favorite);
//    }
//
//    public  void addBasket(Basket basket) {
//        if (baskets == null) {
//            baskets = new ArrayList<>();
//        }
//        baskets.add(basket);
//    }
}

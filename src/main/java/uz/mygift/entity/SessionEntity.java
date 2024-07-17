package uz.mygift.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import uz.mygift.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "session")
@SQLRestriction("deleted= false")
public class SessionEntity extends BaseEntity {
    String sessionId;
    @OneToMany(mappedBy = "sessionEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Basket> baskets;
    @OneToMany(mappedBy = "sessionEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Favorite> favorites;
    @OneToMany(mappedBy = "sessionEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Views> views;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;

    public  void addViews(Views view) {
        if (views == null) {
            views = new ArrayList<>();
        }
        views.add(view);
    }
    public  void addFavorite(Favorite favorite) {
        if (favorites == null) {
            favorites = new ArrayList<>();
        }
        favorites.add(favorite);
    }

    public  void addBasket(Basket basket) {
        if (baskets == null) {
            baskets = new ArrayList<>();
        }
        baskets.add(basket);
    }
}

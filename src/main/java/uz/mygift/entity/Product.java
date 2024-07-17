package uz.mygift.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import uz.mygift.base.BaseEntity;
import uz.mygift.enums.Gender;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@SQLRestriction("deleted= false")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "image", "category", "discount"})
public class Product extends BaseEntity {
    String productName;
    String fullName;
    String article;
    Double productPrice;
    String productDescription;

    @Enumerated(value = EnumType.STRING)
    Gender gender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    ProductType productType;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Discount discount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    Category category;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Image image;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Basket> baskets;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Favorite> favorites;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Views> views;

    public  void addViews(Views view) {
        if (views == null) {
            views = new ArrayList<>();
        }
        views.add(view);
    }

    public void addFavorite(Favorite favorite) {
        if (favorites == null) {
            favorites = new ArrayList<>();
        }
        favorites.add(favorite);
    }

    public void addBasket(Basket basket) {
        if (baskets == null) {
            baskets = new ArrayList<>();
        }
        baskets.add(basket);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", article='" + article + '\'' +
                ", productPrice=" + productPrice +
                ", productDescription='" + productDescription + '\'' +
                ", gender=" + gender +
                '}';
    }
}

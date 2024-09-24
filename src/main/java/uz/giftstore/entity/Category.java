package uz.giftstore.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import uz.giftstore.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
@SQLRestriction("deleted= false")
public class Category extends BaseEntity {
    String categoryName;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Product> products;

    public void addProduct(Product product){
        if (products== null){
            products = new ArrayList<>();
        }
        products.add(product);
    }
    public void removeProduct(Product product){
        if (products== null){
            products = new ArrayList<>();
        }
        products.remove(product);
    }
//    public void updateProduct(int index,Product product){
//        if (products== null){
//            products = new ArrayList<>();
//        }
//        products.set(index,product);
//    }
}

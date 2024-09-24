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
@Table(name = "product_type")
@SQLRestriction("deleted= false")
public class ProductType extends BaseEntity {
    String productType;
    @OneToMany(mappedBy = "productType",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Product> products;
    public void addProduct(Product product){
        if (products==null){
            products = new ArrayList<>();
        }
        products.add(product);
    }
    public void removeProduct(Product product){
        products.remove(product);
    }
}

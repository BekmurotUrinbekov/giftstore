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
@Table(name = "services_type")
@SQLRestriction("deleted= false")
public class ServicesType extends BaseEntity {
    String serviceTypeName;
    @OneToMany(mappedBy = "servicesType",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Services> services;

    public void addServices(Services service){
        if (services == null){
            services=new ArrayList<>();
        }
        services.add(service);
    }
}

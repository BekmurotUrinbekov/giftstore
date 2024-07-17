package uz.mygift.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import uz.mygift.base.BaseEntity;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "services")
@SQLRestriction("deleted= false")
public class Services extends BaseEntity {
    String servicesName;
    Double servicePrice;
    @Column(nullable = false,columnDefinition = "INT DEFAULT 1")
    Integer fromProductAmount;
//    @Column(nullable = false,columnDefinition = "INT DEFAULT (fromProductAmount) CHECK (toProductAmount >= fromProductAmount)")
    Integer toProductAmount;
    String serviceDescription;
    @ManyToOne
    @JsonBackReference
    ServicesType servicesType;

    @OneToMany(mappedBy = "services",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Image> images;
}

package uz.giftstore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.web.multipart.MultipartFile;
import uz.giftstore.base.BaseEntity;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
@SQLRestriction("deleted= false")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "aboutUs", "partner","product","discount","services"})
public class Image extends BaseEntity {
    String imageId;
    String name;
    String contentType;
    Long size;
    String type;
    String path;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "about_us_id")
    @JsonBackReference
    AboutUs aboutUs;
    @OneToOne
    Product product;
    @OneToOne(fetch = FetchType.LAZY)
    Partner partner;
    @OneToOne(cascade = CascadeType.ALL)
    Discount discount;
    @ManyToOne
    @JsonBackReference
    Services services;

    public Image(MultipartFile multipartFile) {
        String[] split = multipartFile.getContentType().split("/");
        LocalDate date = LocalDate.now();
        UUID uuid = UUID.randomUUID();
        setImageId(uuid.toString());
        setName(multipartFile.getOriginalFilename());
        setSize(multipartFile.getSize());
        setContentType(multipartFile.getContentType());
        setType(split[1]);
        String path = "./file/" + date.getYear() + "/" +
                date.getMonthValue() + "/" + date.getDayOfMonth() + "/" +
                split[0] + "/" + uuid + "." + getType();
        setPath(path);
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId='" + imageId + '\'' +
                ", name='" + name + '\'' +
                ", contentType='" + contentType + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}

package uz.mygift.base;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDateTime;


//@Entity
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class  BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP default NOW()", nullable = false, updatable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    protected LocalDateTime createdAt;


    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    protected LocalDateTime updatedAt;

    protected Boolean deleted = Boolean.FALSE;

}

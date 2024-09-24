package uz.giftstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import uz.giftstore.base.BaseEntity;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "log_error")
@SQLRestriction("deleted= false")
public class LogEntry extends BaseEntity {

    String loggerName;
    String logLevel;
    @Column(columnDefinition = "TEXT")
    String message;
    @Column(columnDefinition = "TEXT")
    String exception;

}
package uz.giftstore.dto.req;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ReqImageDto {

    Long aboutUs_id;
    Long product_id;
    Long partner_id;
    Long discount_id;
    Long services_id;

}

package uz.mygift.service.mapper;

import uz.mygift.dto.req.ReqDiscountDto;
import uz.mygift.dto.res.ResDiscountDto;
import uz.mygift.entity.Discount;
public class DiscountMapper {
    public static ResDiscountDto discountToResDiscountDto(Discount discount) {
        if (discount == null) {
            return null;
        }

        ResDiscountDto resDiscountDto = new ResDiscountDto();
        resDiscountDto.setDiscountPrice(discount.getDiscountPrice());
        resDiscountDto.setDeadline(discount.getDeadline());
//        resDiscountDto.setImageDto(imageToResImageDto(discount.getImage()));
//        resDiscountDto.setProductDto(productToResProductDto(discount.getProduct()));

        return resDiscountDto;
    }

    public static Discount reqDiscountDtoToDiscount(ReqDiscountDto reqDiscountDto) {
        if (reqDiscountDto == null) {
            return null;
        }

        Discount discount = new Discount();
        discount.setDiscountPrice(reqDiscountDto.getDiscountPrice());
        discount.setDeadline(reqDiscountDto.getDeadline());
//        discount.setImage(fetchImageById(reqDiscountDto.getImage_id()));
//        discount.setProduct(fetchProductById(reqDiscountDto.getProduct_id()));

        return discount;
    }
}

package uz.giftstore.service.mapper;

import uz.giftstore.dto.req.ReqProductDto;
import uz.giftstore.dto.res.ResProductDto;
import uz.giftstore.entity.Product;
import uz.giftstore.enums.Gender;
public class ProductMapper {

    public static ResProductDto productToResProductDto(Product product) {
               ResProductDto resProductDto = new ResProductDto();
        resProductDto.setProductName(product.getProductName());
        resProductDto.setFullName(product.getFullName());
        resProductDto.setArticle(product.getArticle());
        resProductDto.setProductPrice(product.getProductPrice());
        resProductDto.setProductDescription(product.getProductDescription());
        resProductDto.setGender(product.getGender().name());
        return resProductDto;
    }

    public static Product reqProductDtoToProduct(ReqProductDto reqProductDto) {
               Product product = new Product();
        product.setProductName(reqProductDto.getProductName());
        product.setFullName(reqProductDto.getFullName());
        product.setProductPrice(reqProductDto.getProductPrice());
        product.setProductDescription(reqProductDto.getProductDescription());
        product.setGender(Gender.valueOf(reqProductDto.getGender()));


        return product;
    }

}

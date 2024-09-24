package uz.giftstore.service.mapper;


import uz.giftstore.dto.req.ReqProductTypeDto;
import uz.giftstore.dto.res.ResProducDto;
import uz.giftstore.entity.ProductType;

public class ProductTypeMapper {

    public static ResProducDto ProductTypeToResProductTypeDto(ProductType productType) {
        if (productType == null) {
            return null;
        }

        ResProducDto resProductTypeDto = new ResProducDto();
        resProductTypeDto.setProductType(productType.getProductType());
//        resProductTypeDto.setProducts(productType.getProducts().stream()
//                .map(this::ProductToResProductDto)
//                .collect(Collectors.toList()));

        return resProductTypeDto;
    }

    public static ProductType ReqProductTypeDtoToProductType(ReqProductTypeDto reqProductTypeDto) {
        if (reqProductTypeDto == null) {
            return null;
        }

        ProductType productType = new ProductType();
        productType.setProductType(reqProductTypeDto.getProductType());

        return productType;
    }

}

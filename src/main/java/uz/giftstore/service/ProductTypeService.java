package uz.giftstore.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.giftstore.dto.req.ReqProductTypeDto;
import uz.giftstore.dto.res.ResProducDto;
import uz.giftstore.entity.ProductType;

import java.util.Optional;


public interface ProductTypeService {

    ResponseEntity<ResProducDto> createProductType(ReqProductTypeDto reqProductTypeDto);

    ResponseEntity<ResProducDto> updateProductType(Long productTypeId, ReqProductTypeDto reqProductTypeDto);

    ResponseEntity<Boolean> deleteProductType(Long productTypeId);

    ResponseEntity<ResProducDto> getProductType(Long productTypeId);

    ProductType getProductTypeEntity(Long productTypeId);

    Optional<ProductType> findProductTypeEntity(Long productTypeId);


    ResponseEntity<Page<ResProducDto>> getAll();

    Page<ProductType> getAllEntity();



}

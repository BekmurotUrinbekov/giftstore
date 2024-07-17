package uz.mygift.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.mygift.dto.req.ReqProductTypeDto;
import uz.mygift.dto.res.ResProducDto;
import uz.mygift.entity.ProductType;
import uz.mygift.repository.ProductTypeRepository;
import uz.mygift.service.ProductTypeService;
import uz.mygift.service.mapper.ProductTypeMapper;

import java.util.Optional;
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    private final ProductTypeRepository productTypeRepository;

    public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public ResponseEntity<ResProducDto> createProductType(ReqProductTypeDto reqProductTypeDto) {
        ProductType productType = ProductTypeMapper.ReqProductTypeDtoToProductType(reqProductTypeDto);
        productTypeRepository.save(productType);
        return ResponseEntity.ok(ProductTypeMapper.ProductTypeToResProductTypeDto(productType));
    }

    @Override
    public ResponseEntity<ResProducDto> updateProductType(Long productTypeId, ReqProductTypeDto reqProductTypeDto) {
        ProductType productTypeEntity = getProductTypeEntity(productTypeId);
        productTypeEntity.setProductType(reqProductTypeDto.getProductType());
        productTypeRepository.save(productTypeEntity);
        return ResponseEntity.ok(ProductTypeMapper.ProductTypeToResProductTypeDto(productTypeEntity));
    }

    @Override
    public ResponseEntity<Boolean> deleteProductType(Long productTypeId) {
        ProductType productTypeEntity = getProductTypeEntity(productTypeId);
        productTypeEntity.setDeleted(true);
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<ResProducDto> getProductType(Long productTypeId) {
        return ResponseEntity.ok(ProductTypeMapper.ProductTypeToResProductTypeDto(getProductTypeEntity(productTypeId)));
    }

    @Override
    public ProductType getProductTypeEntity(Long productTypeId) {
        Optional<ProductType> productTypeEntity = findProductTypeEntity(productTypeId);
        if (productTypeEntity.isPresent()){
            return productTypeEntity.get();
        }
        throw new EntityNotFoundException("product type entity not found");
    }

    @Override
    public Optional<ProductType> findProductTypeEntity(Long productTypeId) {
        return productTypeRepository.findById(productTypeId);
    }

    @Override
    public ResponseEntity<Page<ResProducDto>> getAll() {
        return ResponseEntity.ok(getAllEntity().map(ProductTypeMapper::ProductTypeToResProductTypeDto));
    }

    @Override
    public Page<ProductType> getAllEntity() {
        return productTypeRepository.findAll(PageRequest.of(0,20));
    }
}

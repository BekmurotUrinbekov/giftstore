package uz.giftstore.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.giftstore.base.BaseUrl;
import uz.giftstore.dto.req.ReqProductTypeDto;
import uz.giftstore.dto.res.ResProducDto;
import uz.giftstore.service.impl.ProductTypeServiceImpl;

@RestController
@RequestMapping(BaseUrl.PRODUCT_TYPE)
@RequiredArgsConstructor
public class ProductTypeController {
    private final ProductTypeServiceImpl ProductTypeProductType;

    @GetMapping(BaseUrl.GET_ALL)
    public ResponseEntity<Page<ResProducDto>> getAll(){
        return ProductTypeProductType.getAll();
    }

    @GetMapping(BaseUrl.DELETE_BY_ID)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
        return ProductTypeProductType.deleteProductType(id);
    }
    @PostMapping(BaseUrl.UPDATE_BY_ID)
    public ResponseEntity<ResProducDto> updateProductType(@PathVariable Long id, @Valid  @RequestBody ReqProductTypeDto reqProductTypeDto){
        return ProductTypeProductType.updateProductType(id,reqProductTypeDto);
    }

    @PostMapping(BaseUrl.CREATE)
    public ResponseEntity<ResProducDto> createProductType(@Valid @RequestBody ReqProductTypeDto reqProductTypeDto){
        return ProductTypeProductType.createProductType(reqProductTypeDto);
    }
    @GetMapping(BaseUrl.FIND_BY_ID)
    public ResponseEntity<ResProducDto> findById(@PathVariable("id") Long id){
        return ProductTypeProductType.getProductType(id);
    }


}

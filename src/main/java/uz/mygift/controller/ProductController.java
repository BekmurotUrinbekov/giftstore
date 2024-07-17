package uz.mygift.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mygift.base.BaseUrl;
import uz.mygift.dto.req.ReqProductDto;
import uz.mygift.dto.res.ResProductDto;
import uz.mygift.dto.upd.UpdateProductDto;
import uz.mygift.service.impl.ProductServiceImpl;

@RestController
@RequestMapping(BaseUrl.PRODUCT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;

    @GetMapping(BaseUrl.GET_ALL)
    public ResponseEntity<Page<ResProductDto>> getAll(){
        return productService.getAll();
    }

    @GetMapping(BaseUrl.DELETE_BY_ID)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }
    @PostMapping(BaseUrl.UPDATE_BY_ID)
    public ResponseEntity<ResProductDto> updateProductType(@PathVariable Long id, @Valid  @RequestBody UpdateProductDto productDto){
        return productService.updateProduct(id,productDto);
    }

    @PostMapping(BaseUrl.CREATE)
    public ResponseEntity<ResProductDto> createProductType(@Valid @RequestBody ReqProductDto reqProductDto){
        return productService.createProduct(reqProductDto);
    }
    @GetMapping(BaseUrl.FIND_BY_ID)
    public ResponseEntity<ResProductDto> findById(@PathVariable("id") Long id){
        return productService.getProduct(id);
    }


}

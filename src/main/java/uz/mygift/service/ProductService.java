package uz.mygift.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.mygift.dto.req.ReqProductDto;
import uz.mygift.dto.res.ResProductDto;
import uz.mygift.dto.upd.UpdateProductDto;
import uz.mygift.entity.Product;

import java.util.Optional;


public interface ProductService {

    ResponseEntity<ResProductDto> createProduct(ReqProductDto reqProductDto);

    ResponseEntity<ResProductDto> updateProduct(Long productId, UpdateProductDto updateProductDto);

    ResponseEntity<Boolean> deleteProduct(Long productId);

    ResponseEntity<ResProductDto> getProduct(Long productId);

    Product getProductEntity(Long productId);

    Optional<Product> findProductEntity(Long productId);


    ResponseEntity<Page<ResProductDto>> getAll();

    Page<Product> getAllEntity();



}

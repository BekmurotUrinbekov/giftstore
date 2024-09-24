package uz.giftstore.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.giftstore.dto.req.ReqProductDto;
import uz.giftstore.dto.res.ResProductDto;
import uz.giftstore.dto.upd.UpdateProductDto;
import uz.giftstore.entity.Product;

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

package uz.mygift.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.mygift.dto.req.ReqDiscountDto;
import uz.mygift.dto.upd.UpdateDiscountDto;
import uz.mygift.dto.res.ResDiscountDto;
import uz.mygift.entity.Discount;

import java.util.Optional;


public interface DiscountService {

    ResponseEntity<ResDiscountDto> createDiscount(ReqDiscountDto reqDiscountDto);

    ResponseEntity<ResDiscountDto> updateDiscount(Long discountId, UpdateDiscountDto reqDiscountUpdateDto);
    ResponseEntity<Boolean> deleteDiscount(Long discountId);

    ResponseEntity<ResDiscountDto> getDiscount(Long discountId);

    Discount getDiscountEntity(Long discountId);

    Optional<Discount> findDiscountEntity(Long discountId);


    ResponseEntity<Page<ResDiscountDto>> getAll();

    Page<Discount> getAllEntity();



}

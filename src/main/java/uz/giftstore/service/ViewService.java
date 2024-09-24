package uz.giftstore.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.giftstore.dto.res.ResProductDto;
import uz.giftstore.dto.res.ResReactionDto;
import uz.giftstore.dto.res.ResViewDto;
import uz.giftstore.entity.Views;

import java.util.List;
import java.util.Optional;


public interface ViewService {

    ResponseEntity<ResViewDto> createView(Long productId, HttpServletRequest request);

//    ResponseEntity<Boolean> increaseView(Long viewId);
//    ResponseEntity<Boolean> decreaseView(Long viewId);

    ResponseEntity<Boolean> deleteView(Long viewId);
    ResponseEntity<List<ResProductDto>> getUserViewsProducts(Long userId);
    ResponseEntity<List<ResReactionDto>> getTopViewsProducts(Integer page, Integer size);
    ResponseEntity<List<ResReactionDto>> findMostViewsProducts(Integer days, Integer page, Integer size);
    ResponseEntity<Long> countViewByProductId(Long productId);

    ResponseEntity<ResViewDto> getView(Long viewId);

    Views getViewEntity(Long viewId);

    Optional<Views> findViewEntity(Long viewId);


    ResponseEntity<Page<ResViewDto>> getAll();

    Page<Views> getAllEntity();



}

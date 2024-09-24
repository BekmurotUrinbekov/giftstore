package uz.giftstore.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.giftstore.base.BaseUrl;
import uz.giftstore.dto.res.ResProductDto;
import uz.giftstore.dto.res.ResReactionDto;
import uz.giftstore.dto.res.ResViewDto;
import uz.giftstore.service.impl.ViewServiceImpl;

import java.util.List;

@RestController
@RequestMapping(BaseUrl.VIEW)
@RequiredArgsConstructor
public class ViewController {
    private final ViewServiceImpl viewService;

    @GetMapping(BaseUrl.GET_ALL)
    public ResponseEntity<Page<ResViewDto>> getAll(){
        return viewService.getAll();
    }

    @GetMapping(BaseUrl.DELETE_BY_ID)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
        return viewService.deleteView(id);
    }

    @GetMapping(BaseUrl.CREATE_WHIT_PRODUCT_ID)
    public ResponseEntity<ResViewDto> createView(@PathVariable("productId") Long productId, HttpServletRequest request){
        return viewService.createView(productId,request);
    }
    @GetMapping(BaseUrl.FIND_BY_ID)
    public ResponseEntity<ResViewDto> findById(@PathVariable("id") Long id){
        return viewService.getView(id);
    }
    @GetMapping(BaseUrl.REACTION_PRODUCTS_WITH_USER_ID)
    public ResponseEntity<List<ResProductDto>> getUserFavoriteProducts(@PathVariable("userId") Long userId){
        return viewService.getUserViewsProducts(userId);
    }
    @GetMapping(BaseUrl.REACTION_ALL_TIME)
    public ResponseEntity<List<ResReactionDto>> getTopViewsProducts(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return viewService.getTopViewsProducts(page,size);
    }
    @GetMapping(BaseUrl.REACTION_WEEK)
    public ResponseEntity<List<ResReactionDto>> findMostViewsProductsOfWeek(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        return viewService.findMostViewsProducts(7,page,size);
    }
    @GetMapping(BaseUrl.REACTION_MONTH)
    public ResponseEntity<List<ResReactionDto>> findMostViewsProductsOfMonth(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        return viewService.findMostViewsProducts(30,page,size);
    }
    @GetMapping(BaseUrl.REACTION_YEAR)
    public ResponseEntity<List<ResReactionDto>> findMostViewsProductsOfYear(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        return viewService.findMostViewsProducts(365,page,size);
    }
//    @GetMapping(BaseUrl.INCREASE)
//    private ResponseEntity<Boolean> increaseViewMount(@PathVariable("id") Long id){
//        return viewService.increaseView(id);
//    }

//    @GetMapping(BaseUrl.DECREASE)
//    private ResponseEntity<Boolean> decreaseViewMount(@PathVariable("id") Long id){
//        return viewService.decreaseView(id);
//    }
}

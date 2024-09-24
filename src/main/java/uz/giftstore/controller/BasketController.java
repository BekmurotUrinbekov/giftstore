package uz.giftstore.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.giftstore.base.BaseUrl;
import uz.giftstore.dto.res.ResBasketDto;
import uz.giftstore.dto.res.ResProductDto;
import uz.giftstore.dto.res.ResReactionDto;
import uz.giftstore.service.impl.BasketServiceImpl;

import java.util.List;

@RestController
@RequestMapping(BaseUrl.BASKET)
@RequiredArgsConstructor
public class BasketController {
    private final BasketServiceImpl basketService;

    @GetMapping(BaseUrl.GET_ALL)
    public ResponseEntity<Page<ResBasketDto>> getAll(){
        return basketService.getAll();
    }

    @GetMapping(BaseUrl.DELETE_BY_ID)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
        return basketService.deleteBasket(id);
    }

    @GetMapping(BaseUrl.CREATE_WHIT_PRODUCT_ID)
    public ResponseEntity<ResBasketDto> createView(@PathVariable("productId") Long productId, HttpServletRequest request){
        return basketService.createBasket(productId,request);
    }
    @GetMapping(BaseUrl.REACTION_PRODUCTS)
    public ResponseEntity<List<ResProductDto>> getUserFavoriteProducts(HttpServletRequest request){
        return basketService.getUserBasketProducts(request);
    }
    @GetMapping(BaseUrl.REACTION_ALL_TIME)
    public ResponseEntity<List<ResReactionDto>> getTopBasketProducts(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return basketService.getTopBasketProducts(page,size);
    }
    @GetMapping(BaseUrl.REACTION_WEEK)
    public ResponseEntity<List<ResReactionDto>> findMostBasketProductsOfWeek(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        return basketService.findMostBasketProducts(7,page,size);
    }
    @GetMapping(BaseUrl.REACTION_MONTH)
    public ResponseEntity<List<ResReactionDto>> findMostBasketProductsOfMonth(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        return basketService.findMostBasketProducts(30,page,size);
    }
    @GetMapping(BaseUrl.REACTION_YEAR)
    public ResponseEntity<List<ResReactionDto>> findMostBasketProductsOfYear(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        return basketService.findMostBasketProducts(365,page,size);
    }
    @GetMapping(BaseUrl.FIND_BY_ID)
    public ResponseEntity<ResBasketDto> findById(@PathVariable("id") Long id){
        return basketService.getBasket(id);
    }


}

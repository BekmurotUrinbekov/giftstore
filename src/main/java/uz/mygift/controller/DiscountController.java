package uz.mygift.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mygift.base.BaseUrl;
import uz.mygift.dto.req.ReqDiscountDto;
import uz.mygift.dto.res.ResDiscountDto;
import uz.mygift.dto.upd.UpdateDiscountDto;
import uz.mygift.service.impl.DiscountServiceImpl;

@RestController
@RequestMapping(BaseUrl.DISCOUNT)
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountServiceImpl discountService;

    @GetMapping(BaseUrl.GET_ALL)
    public ResponseEntity<Page<ResDiscountDto>> getAll(){
        return discountService.getAll();
    }

    @GetMapping(BaseUrl.DELETE_BY_ID)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
        return discountService.deleteDiscount(id);
    }

    @GetMapping(BaseUrl.CREATE)
    public ResponseEntity<ResDiscountDto> createDiscount(@Valid @RequestBody ReqDiscountDto reqDiscountDto){
        return discountService.createDiscount(reqDiscountDto);
    }
    @PostMapping(BaseUrl.UPDATE_BY_ID)
    public ResponseEntity<ResDiscountDto> updateDiscount(@PathVariable Long id,@Valid @RequestBody UpdateDiscountDto updateDiscountDto){
        return discountService.updateDiscount(id,updateDiscountDto);
    }
    @GetMapping(BaseUrl.FIND_BY_ID)
    public ResponseEntity<ResDiscountDto> findById(@PathVariable("id") Long id){
        return discountService.getDiscount(id);
    }


}

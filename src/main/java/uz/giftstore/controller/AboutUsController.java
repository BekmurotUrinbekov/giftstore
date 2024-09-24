package uz.giftstore.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.giftstore.base.BaseUrl;
import uz.giftstore.dto.req.ReqAboutUsDto;
import uz.giftstore.dto.res.ResAboutUsDto;
import uz.giftstore.dto.upd.UpdateAboutUsDto;
import uz.giftstore.service.impl.AboutUsServiceImpl;

@RestController
@RequestMapping(BaseUrl.ABOUT_US)
@RequiredArgsConstructor
public class AboutUsController {
    private final AboutUsServiceImpl aboutUsAboutUs;

    @GetMapping(BaseUrl.GET_ALL)
    public ResponseEntity<Page<ResAboutUsDto>> getAll(){
        return  aboutUsAboutUs.getAll();
    }

    @GetMapping(BaseUrl.DELETE_BY_ID)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
        return aboutUsAboutUs.deleteAboutUs(id);
    }
    @PostMapping(BaseUrl.UPDATE_BY_ID)
    public ResponseEntity<ResAboutUsDto> updateAboutUs(@PathVariable Long id, @Valid @RequestBody UpdateAboutUsDto updateAboutUsDto){
        return aboutUsAboutUs.updateAboutUs(id,updateAboutUsDto);
    }

    @PostMapping(BaseUrl.CREATE)
    public ResponseEntity<ResAboutUsDto> createAboutUs(@Valid @RequestBody ReqAboutUsDto reqAboutUsDto){
        return aboutUsAboutUs.createAboutUs(reqAboutUsDto);
    }
    @GetMapping(BaseUrl.FIND_BY_ID)
    public ResponseEntity<ResAboutUsDto> findById(@PathVariable("id") Long id){
        return aboutUsAboutUs.getAboutUs(id);
    }


}

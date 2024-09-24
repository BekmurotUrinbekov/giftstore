package uz.giftstore.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.giftstore.dto.req.ReqAboutUsDto;
import uz.giftstore.dto.res.ResAboutUsDto;
import uz.giftstore.dto.upd.UpdateAboutUsDto;
import uz.giftstore.entity.AboutUs;

import java.util.Optional;


public interface AboutUsService {

    ResponseEntity<ResAboutUsDto> createAboutUs(ReqAboutUsDto reqAboutUsDto);

    ResponseEntity<ResAboutUsDto> updateAboutUs(Long aboutUsId, UpdateAboutUsDto updateAboutUsDto);

    ResponseEntity<Boolean> deleteAboutUs(Long aboutUsId);

    ResponseEntity<ResAboutUsDto> getAboutUs(Long aboutUsId);

    AboutUs getAboutUsEntity(Long aboutUsId);

    Optional<AboutUs> findAboutUsEntity(Long aboutUsId);


    ResponseEntity<Page<ResAboutUsDto>> getAll();

    Page<AboutUs> getAllEntity();
//    ResponseEntity<List<ResAboutUsDto>> getAll();
//
//    List<AboutUs> getAllEntity();



}

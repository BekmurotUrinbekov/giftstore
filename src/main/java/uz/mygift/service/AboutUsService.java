package uz.mygift.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.mygift.dto.req.ReqAboutUsDto;
import uz.mygift.dto.res.ResAboutUsDto;
import uz.mygift.dto.upd.UpdateAboutUsDto;
import uz.mygift.entity.AboutUs;

import java.util.List;
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

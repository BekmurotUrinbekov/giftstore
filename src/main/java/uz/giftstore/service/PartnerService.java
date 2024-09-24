package uz.giftstore.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.giftstore.dto.req.ReqPartnerDto;
import uz.giftstore.dto.res.ResPartnerDto;
import uz.giftstore.dto.upd.UpdatePartnerDto;
import uz.giftstore.entity.Partner;

import java.util.Optional;


public interface PartnerService {

    ResponseEntity<ResPartnerDto> createPartner(ReqPartnerDto reqPartnerDto);

    ResponseEntity<ResPartnerDto> updatePartner(Long partnerId, UpdatePartnerDto updatePartnerDto);

    ResponseEntity<Boolean> deletePartner(Long partnerId);

    ResponseEntity<ResPartnerDto> getPartner(Long partnerId);

    Partner getPartnerEntity(Long partnerId);

    Optional<Partner> findPartnerEntity(Long partnerId);


    ResponseEntity<Page<ResPartnerDto>> getAll();

    Page<Partner> getAllEntity();



}

package uz.mygift.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.mygift.dto.req.ReqPartnerDto;
import uz.mygift.dto.res.ResPartnerDto;
import uz.mygift.dto.upd.UpdatePartnerDto;
import uz.mygift.entity.Partner;

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

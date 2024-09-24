package uz.giftstore.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.giftstore.dto.req.ReqServiceTypeDto;
import uz.giftstore.dto.res.ResServiceTypeDto;
import uz.giftstore.entity.ServicesType;

import java.util.Optional;


public interface ServiceTypeService {

    ResponseEntity<ResServiceTypeDto> createServicesType(ReqServiceTypeDto reqServiceTypeDto);

    ResponseEntity<ResServiceTypeDto> updateServiceType(Long serviceTypeId, ReqServiceTypeDto reqServiceTypeDto);

    ResponseEntity<Boolean> deleteServicesType(Long serviceTypeId);

    ResponseEntity<ResServiceTypeDto> getServicesType(Long serviceTypeId);

    ServicesType getServicesTypeEntity(Long serviceTypeId);

    Optional<ServicesType> findServicesTypeEntity(Long serviceTypeId);


    ResponseEntity<Page<ResServiceTypeDto>> getAll();

    Page<ServicesType> getAllEntity();



}

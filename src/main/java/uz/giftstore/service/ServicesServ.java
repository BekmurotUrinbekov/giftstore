package uz.giftstore.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.giftstore.dto.req.ReqServiceDto;
import uz.giftstore.dto.res.ResServiceDto;
import uz.giftstore.entity.Services;

import java.util.Optional;


public interface ServicesServ {

    ResponseEntity<ResServiceDto> createServices(ReqServiceDto reqServiceDto);

    ResponseEntity<ResServiceDto> updateService(Long serviceId, ReqServiceDto reqServiceDto);

    ResponseEntity<Boolean> deleteServices(Long serviceId);

    ResponseEntity<ResServiceDto> getServices(Long serviceId);

    Services getServicesEntity(Long serviceId);

    Optional<Services> findServicesEntity(Long serviceId);


    ResponseEntity<Page<ResServiceDto>> getAll();

    Page<Services> getAllEntity();



}

package uz.mygift.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.mygift.dto.req.ReqServiceDto;
import uz.mygift.dto.res.ResServiceDto;
import uz.mygift.entity.Services;

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

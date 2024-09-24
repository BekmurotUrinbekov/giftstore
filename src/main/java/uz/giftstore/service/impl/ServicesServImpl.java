package uz.giftstore.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.giftstore.dto.req.ReqServiceDto;
import uz.giftstore.dto.res.ResServiceDto;
import uz.giftstore.entity.Services;
import uz.giftstore.entity.ServicesType;
import uz.giftstore.repository.ServicesRepository;
import uz.giftstore.repository.ServicesTypeRepository;
import uz.giftstore.service.ServicesServ;
import uz.giftstore.service.mapper.ServicesMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicesServImpl implements ServicesServ {
    private final ServicesRepository servicesRepository;
    private final ServicesTypeRepository servicesTypeRepository;
    @Override
    public ResponseEntity<ResServiceDto> createServices(ReqServiceDto reqServiceDto) {
        Optional<ServicesType> servicesType = servicesTypeRepository.findById(reqServiceDto.getServicesType_id());
        if (servicesType.isPresent()){
            Services services = ServicesMapper.reqServiceDtoToService(reqServiceDto);
            services.setServicesType(servicesType.get());
            servicesRepository.save(services);
            return ResponseEntity.ok(ServicesMapper.serviceToResServiceDto(services));
        }
        throw new EntityNotFoundException("service type entity not found");
    }

    @Override
    public ResponseEntity<ResServiceDto> updateService(Long serviceId, ReqServiceDto reqServiceDto) {
        Services servicesEntity = getServicesEntity(serviceId);
        if (reqServiceDto.getServicesName()!=null){
            servicesEntity.setServicesName(reqServiceDto.getServicesName());
        }
        if (reqServiceDto.getServiceDescription()!=null){
            servicesEntity.setServiceDescription(reqServiceDto.getServiceDescription());
        }
        if (reqServiceDto.getServicePrice()!=null){
            servicesEntity.setServicePrice(reqServiceDto.getServicePrice());
        }
        if (reqServiceDto.getToProductAmount()!=null){
            servicesEntity.setToProductAmount(reqServiceDto.getToProductAmount());
        }
        if (reqServiceDto.getFromProductAmount()!=null){
            servicesEntity.setFromProductAmount(reqServiceDto.getFromProductAmount());
        }
        Optional<ServicesType> servicesType = servicesTypeRepository.findById(reqServiceDto.getServicesType_id());
        if (reqServiceDto.getServicesType_id()!=null && servicesType.isPresent()){
            servicesEntity.setServicesType(servicesType.get());
        }
        servicesRepository.save(servicesEntity);
        return ResponseEntity.ok(ServicesMapper.serviceToResServiceDto(servicesEntity));
    }

    @Override
    public ResponseEntity<Boolean> deleteServices(Long serviceId) {
        Services servicesEntity = getServicesEntity(serviceId);
        servicesEntity.setDeleted(true);
        servicesRepository.save(servicesEntity);
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<ResServiceDto> getServices(Long serviceId) {
        return ResponseEntity.ok(ServicesMapper.serviceToResServiceDto(getServicesEntity(serviceId)));
    }

    @Override
    public Services getServicesEntity(Long serviceId) {
        Optional<Services> servicesEntity = findServicesEntity(serviceId);
        if (servicesEntity.isPresent()){
            return servicesEntity.get();
        }
        throw new EntityNotFoundException("service entity not found");
    }

    @Override
    public Optional<Services> findServicesEntity(Long serviceId) {
        return servicesRepository.findById(serviceId);
    }

    @Override
    public ResponseEntity<Page<ResServiceDto>> getAll() {
        return ResponseEntity.ok(getAllEntity().map(ServicesMapper::serviceToResServiceDto));
    }

    @Override
    public Page<Services> getAllEntity() {
        return servicesRepository.findAll(PageRequest.of(0,20));
    }
}

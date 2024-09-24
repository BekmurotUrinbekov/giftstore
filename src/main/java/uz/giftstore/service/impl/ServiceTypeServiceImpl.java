package uz.giftstore.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.giftstore.dto.req.ReqServiceTypeDto;
import uz.giftstore.dto.res.ResServiceTypeDto;
import uz.giftstore.entity.ServicesType;
import uz.giftstore.repository.ServicesTypeRepository;
import uz.giftstore.service.ServiceTypeService;
import uz.giftstore.service.mapper.ServicesTypeMapper;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ServiceTypeServiceImpl implements ServiceTypeService {
    private final ServicesTypeRepository servicesTypeRepository;
    @Override
    public ResponseEntity<ResServiceTypeDto> createServicesType(ReqServiceTypeDto reqServiceTypeDto) {
        ServicesType servicesType=new ServicesType();
        servicesType.setServiceTypeName(reqServiceTypeDto.getServiceTypeName());
        servicesTypeRepository.save(servicesType);
        return ResponseEntity.ok(ServicesTypeMapper.serviceTypeToResServiceTypeDto(servicesType));
    }

    @Override
    public ResponseEntity<ResServiceTypeDto> updateServiceType(Long serviceTypeId, ReqServiceTypeDto reqServiceTypeDto) {
        ServicesType servicesTypeEntity = getServicesTypeEntity(serviceTypeId);
        servicesTypeEntity.setServiceTypeName(reqServiceTypeDto.getServiceTypeName());
        servicesTypeRepository.save(servicesTypeEntity);
        return ResponseEntity.ok(ServicesTypeMapper.serviceTypeToResServiceTypeDto(servicesTypeEntity));
    }

    @Override
    public ResponseEntity<Boolean> deleteServicesType(Long serviceTypeId) {
        ServicesType servicesTypeEntity = getServicesTypeEntity(serviceTypeId);
        servicesTypeEntity.setDeleted(true);
        servicesTypeRepository.save(servicesTypeEntity);
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<ResServiceTypeDto> getServicesType(Long serviceTypeId) {
        return ResponseEntity.ok(ServicesTypeMapper.serviceTypeToResServiceTypeDto(getServicesTypeEntity(serviceTypeId)));
    }

    @Override
    public ServicesType getServicesTypeEntity(Long serviceTypeId) {
        Optional<ServicesType> servicesTypeEntity = findServicesTypeEntity(serviceTypeId);
        if (servicesTypeEntity.isPresent()){
            return servicesTypeEntity.get();
        }
        throw new EntityNotFoundException("serviceType entity not found");
    }

    @Override
    public Optional<ServicesType> findServicesTypeEntity(Long serviceTypeId) {
        return servicesTypeRepository.findById(serviceTypeId);
    }

    @Override
    public ResponseEntity<Page<ResServiceTypeDto>> getAll() {
        return ResponseEntity.ok(getAllEntity().map(ServicesTypeMapper::serviceTypeToResServiceTypeDto));
    }

    @Override
    public Page<ServicesType> getAllEntity() {
        return servicesTypeRepository.findAll(PageRequest.of(0,20));
    }
}

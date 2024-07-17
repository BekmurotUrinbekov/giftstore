package uz.mygift.service.mapper;

import org.springframework.stereotype.Component;
import uz.mygift.dto.req.ReqServiceTypeDto;
import uz.mygift.dto.res.ResServiceTypeDto;
import uz.mygift.entity.Services;
import uz.mygift.entity.ServicesType;

import java.util.List;
import java.util.stream.Collectors;

public class ServicesTypeMapper {
    public static ResServiceTypeDto serviceTypeToResServiceTypeDto(ServicesType servicesType) {
        if (servicesType == null) {
            return null;
        }

        ResServiceTypeDto resServiceTypeDto = new ResServiceTypeDto();
        resServiceTypeDto.setServiceTypeName(servicesType.getServiceTypeName());

        if (servicesType.getServices() != null) {
            List<String> serviceNames = servicesType.getServices().stream()
                    .map(Services::getServicesName)
                    .collect(Collectors.toList());
            resServiceTypeDto.setServicesNames(serviceNames);
        }

        return resServiceTypeDto;
    }

    public static ServicesType reqServiceTypeDtoToServiceType(ReqServiceTypeDto reqServiceTypeDto) {
        if (reqServiceTypeDto == null) {
            return null;
        }

        ServicesType servicesType = new ServicesType();
        servicesType.setServiceTypeName(reqServiceTypeDto.getServiceTypeName());
        return servicesType;
    }
}

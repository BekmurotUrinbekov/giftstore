package uz.giftstore.service.mapper;

import uz.giftstore.dto.req.ReqServiceDto;
import uz.giftstore.dto.res.ResServiceDto;
import uz.giftstore.entity.Services;
public class ServicesMapper {
    public static ResServiceDto serviceToResServiceDto(Services service) {
        if (service == null) {
            return null;
        }

        ResServiceDto resServiceDto = new ResServiceDto();
        resServiceDto.setServicesName(service.getServicesName());
        resServiceDto.setServicePrice(service.getServicePrice());
        resServiceDto.setFromProductAmount(service.getFromProductAmount());
        resServiceDto.setToProductAmount(service.getToProductAmount());
        resServiceDto.setServiceDescription(service.getServiceDescription());
//        resServiceDto.setServiceTypeDto(convertToResServiceTypeDto(service.getServicesType()));

//        if (service.getImages() != null) {
//            List<ResImageDto> imageDtos = service.getImages().stream()
//                    .map(this::convertToResImageDto)
//                    .collect(Collectors.toList());
//            resServiceDto.setResImageDtos(imageDtos);
//        }

        return resServiceDto;
    }

    // Method to convert ReqServiceDto to Services entity
    public static Services reqServiceDtoToService(ReqServiceDto reqServiceDto) {
        if (reqServiceDto == null) {
            return null;
        }

        Services service = new Services();
        service.setServicesName(reqServiceDto.getServicesName());
        service.setServicePrice(reqServiceDto.getServicePrice());
        service.setFromProductAmount(reqServiceDto.getFromProductAmount());
        service.setToProductAmount(reqServiceDto.getToProductAmount());
        service.setServiceDescription(reqServiceDto.getServiceDescription());

//        ServicesType serviceType = new ServicesType();
//        serviceType.setId(reqServiceDto.getServicesType_id());
//        service.setServicesType(serviceType);

        return service;
    }

}

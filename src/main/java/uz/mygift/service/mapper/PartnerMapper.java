package uz.mygift.service.mapper;

import uz.mygift.dto.req.ReqPartnerDto;
import uz.mygift.dto.res.ResPartnerDto;
import uz.mygift.entity.Partner;

public class PartnerMapper {
    public static ResPartnerDto partnerToResPartnerDto(Partner partner) {

        ResPartnerDto resPartnerDto = new ResPartnerDto();
        resPartnerDto.setPartnerName(partner.getPartnerName());
        return resPartnerDto;
    }

    public static Partner reqPartnerDtoToPartner(ReqPartnerDto reqPartnerDto) {

        Partner partner = new Partner();
        partner.setPartnerName(reqPartnerDto.getPartnerName());
        return partner;
    }
}

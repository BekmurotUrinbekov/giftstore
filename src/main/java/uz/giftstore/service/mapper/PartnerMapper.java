package uz.giftstore.service.mapper;

import uz.giftstore.dto.req.ReqPartnerDto;
import uz.giftstore.dto.res.ResPartnerDto;
import uz.giftstore.entity.Partner;

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

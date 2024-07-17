package uz.mygift.service.mapper;

import uz.mygift.dto.req.ReqAboutUsDto;
import uz.mygift.dto.res.ResAboutUsDto;
import uz.mygift.entity.AboutUs;

public class AboutUsMapper {
    public static ResAboutUsDto aboutUsToResAboutUsDto(AboutUs aboutUs) {
        ResAboutUsDto resAboutUsDto = new ResAboutUsDto();
        resAboutUsDto.setContact(aboutUs.getContact());
        resAboutUsDto.setEmail(aboutUs.getEmail());
        resAboutUsDto.setTelegram(aboutUs.getTelegram());
        resAboutUsDto.setInstagram(aboutUs.getInstagram());
        resAboutUsDto.setDescription(aboutUs.getDescription());
        return resAboutUsDto;
    }

    public static AboutUs reqAboutUsDtoToAboutUs(ReqAboutUsDto reqAboutUsDto) {
        AboutUs aboutUs = new AboutUs();
        aboutUs.setContact(reqAboutUsDto.getContact());
        aboutUs.setEmail(reqAboutUsDto.getEmail());
        aboutUs.setTelegram(reqAboutUsDto.getTelegram());
        aboutUs.setInstagram(reqAboutUsDto.getInstagram());
        aboutUs.setDescription(reqAboutUsDto.getDescription());
        return aboutUs;
    }
}

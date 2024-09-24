package uz.giftstore.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.giftstore.dto.req.ReqAboutUsDto;
import uz.giftstore.dto.res.ResAboutUsDto;
import uz.giftstore.dto.upd.UpdateAboutUsDto;
import uz.giftstore.entity.AboutUs;
import uz.giftstore.repository.AboutUsRepository;
import uz.giftstore.repository.ImageRepository;
import uz.giftstore.service.AboutUsService;
import uz.giftstore.service.mapper.AboutUsMapper;

import java.util.Optional;


@Service
public class AboutUsServiceImpl implements AboutUsService {
    private final AboutUsRepository aboutUsRepository;
    private final ImageRepository imageRepository;
    private static final Logger logger = LoggerFactory.getLogger(AboutUsServiceImpl.class);

    public AboutUsServiceImpl(AboutUsRepository aboutUsRepository, ImageRepository imageRepository) {
        this.aboutUsRepository = aboutUsRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public ResponseEntity<ResAboutUsDto> createAboutUs(ReqAboutUsDto reqAboutUsDto) {
        AboutUs aboutUs = AboutUsMapper.reqAboutUsDtoToAboutUs(reqAboutUsDto);
        aboutUsRepository.save(aboutUs);
        return ResponseEntity.ok(AboutUsMapper.aboutUsToResAboutUsDto(aboutUs));
    }

//    @Override
//    public ResponseEntity<ResAboutUsDto> createAboutUs(ReqAboutUsDto reqAboutUsDto) {
//        Optional<Image> imageOptional = imageRepository.findByImageId(reqAboutUsDto.getPicture_id());
//        if (imageOptional.isPresent()) {
//            Image image = imageOptional.get();
//            AboutUs aboutUs = AboutUsMapper.reqAboutUsDtoToAboutUs(reqAboutUsDto);
//
//            // Set the Image reference in AboutUs
//            aboutUs.addImage(image);
//
//            // Save AboutUs entity first
//            AboutUs savedAboutUs = aboutUsRepository.save(aboutUs);
//
//            // Update the Image entity to include the AboutUs entity
//            image.addAboutUs(savedAboutUs);
//            imageRepository.save(image);  // Save Image to update the relationship
//
//            return ResponseEntity.ok(AboutUsMapper.aboutUsToResAboutUsDto(savedAboutUs));
//        } else {
//            throw new EntityNotFoundException("Image not found");
//        }
//    }



    @Override
    public ResponseEntity<ResAboutUsDto> updateAboutUs(Long aboutUsId, UpdateAboutUsDto updateAboutUsDto) {
        AboutUs aboutUsEntity = getAboutUsEntity(aboutUsId);
        if (updateAboutUsDto.getContact()!=null){
            aboutUsEntity.setContact(updateAboutUsDto.getContact());
        }
        if (updateAboutUsDto.getEmail()!=null){
            aboutUsEntity.setEmail(updateAboutUsDto.getEmail());
        }
        if (updateAboutUsDto.getDescription()!=null){
            aboutUsEntity.setDescription(updateAboutUsDto.getDescription());
        }
        if (updateAboutUsDto.getTelegram()!=null){
            aboutUsEntity.setTelegram(updateAboutUsDto.getTelegram());
        }
        if (updateAboutUsDto.getInstagram()!=null){
            aboutUsEntity.setInstagram(updateAboutUsDto.getInstagram());
        }
        aboutUsRepository.save(aboutUsEntity);
        return ResponseEntity.ok(AboutUsMapper.aboutUsToResAboutUsDto(aboutUsEntity));
    }

    @Override
    public ResponseEntity<Boolean> deleteAboutUs(Long aboutUsId) {
        AboutUs aboutUsEntity = getAboutUsEntity(aboutUsId);
        aboutUsEntity.setDeleted(true);
        aboutUsRepository.save(aboutUsEntity);
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<ResAboutUsDto> getAboutUs(Long aboutUsId) {
        return ResponseEntity.ok(AboutUsMapper.aboutUsToResAboutUsDto(getAboutUsEntity(aboutUsId)));
    }

    @Override
    public AboutUs getAboutUsEntity(Long aboutUsId) {
        Optional<AboutUs> aboutUsEntity = findAboutUsEntity(aboutUsId);
        if (aboutUsEntity.isPresent()){
            return aboutUsEntity.get();
        }
        throw new EntityNotFoundException("about us entity not found");
    }

    @Override
    public Optional<AboutUs> findAboutUsEntity(Long aboutUsId) {
        return aboutUsRepository.findById(aboutUsId);
    }

//    @Override
//    public ResponseEntity<List<ResAboutUsDto>> getAll() {
//        List<ResAboutUsDto> list = getAllEntity().stream().map(AboutUsMapper::aboutUsToResAboutUsDto).toList();
//        return ResponseEntity.ok(list);
//    }
//    @Override
//    public ResponseEntity<List<ResAboutUsDto>> getAll() {
//        System.out.println("555555555"+getAllEntity());
//        return ResponseEntity.ok(null);
//    }
//
//    @Override
//    public List<AboutUs> getAllEntity() {
//        List<AboutUs> all = aboutUsRepository.findAll();
//        System.out.println(all);
//        logger.info("Fetched {} AboutUs entities: {}", all.size(), all);
//        return all;
//    }
    @Override
    public ResponseEntity<Page<ResAboutUsDto>> getAll() {
        Page<ResAboutUsDto> map = getAllEntity().map(AboutUsMapper::aboutUsToResAboutUsDto);
        System.out.println(map);
        return ResponseEntity.ok(map);
    }

    @Override
    public Page<AboutUs> getAllEntity() {
        System.out.println(aboutUsRepository.findAll());
        return aboutUsRepository.findAll(PageRequest.of(0,20));
    }
}

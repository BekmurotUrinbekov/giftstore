package uz.giftstore.service.impl;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.giftstore.dto.req.ReqPartnerDto;
import uz.giftstore.dto.res.ResPartnerDto;
import uz.giftstore.dto.upd.UpdatePartnerDto;
import uz.giftstore.entity.AboutUs;
import uz.giftstore.entity.Image;
import uz.giftstore.entity.Partner;
import uz.giftstore.repository.AboutUsRepository;
import uz.giftstore.repository.ImageRepository;
import uz.giftstore.repository.PartnerRepository;
import uz.giftstore.service.PartnerService;
import uz.giftstore.service.mapper.PartnerMapper;

import java.util.Optional;

@Service
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository partnerRepository;
    private final AboutUsRepository aboutUsRepository;
    private final ImageRepository imageRepository;

    public PartnerServiceImpl( PartnerRepository partnerRepository, AboutUsRepository aboutUsRepository, ImageRepository imageRepository) {
        this.partnerRepository = partnerRepository;
        this.aboutUsRepository = aboutUsRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public ResponseEntity<ResPartnerDto> createPartner(ReqPartnerDto reqPartnerDto) {
        Optional<AboutUs> aboutUs = aboutUsRepository.findById(reqPartnerDto.getAboutUs_id());
        Optional<Image> image = imageRepository.findByImageId(reqPartnerDto.getImage_id());
        if (aboutUs.isPresent()&&image.isPresent()){
            Partner partner = PartnerMapper.reqPartnerDtoToPartner(reqPartnerDto);
            partner.setImage(image.get());
            partner.setAboutUs(aboutUs.get());
            partnerRepository.save(partner);
            aboutUs.get().addPartner(partner);
            aboutUsRepository.save(aboutUs.get());
            image.get().setPartner(partner);
            imageRepository.save(image.get());
            return ResponseEntity.ok(PartnerMapper.partnerToResPartnerDto(partner));
        }
        throw new EntityNotFoundException("entity not found");
    }

    @Override
    public ResponseEntity<ResPartnerDto> updatePartner(Long partnerId, UpdatePartnerDto updatePartnerDto) {
        Partner partnerEntity = getPartnerEntity(partnerId);
        if (updatePartnerDto.getPartnerName()!=null){
            partnerEntity.setPartnerName(updatePartnerDto.getPartnerName());
        }
        if (updatePartnerDto.getImage_id()!=null){
            Optional<Image> image = imageRepository.findById(updatePartnerDto.getImage_id());
            if (image.isPresent()){
                partnerEntity.setImage(image.get());
            }
            throw new EntityNotFoundException("image entity not found");
        }
        if (updatePartnerDto.getAboutUs_id()!=null){
            Optional<AboutUs> aboutUs = aboutUsRepository.findById(updatePartnerDto.getAboutUs_id());
            if (aboutUs.isPresent()){
                partnerEntity.setAboutUs(aboutUs.get());
            }
            throw new EntityNotFoundException("about us entity not found");
        }
        partnerRepository.save(partnerEntity);
        return ResponseEntity.ok(PartnerMapper.partnerToResPartnerDto(partnerEntity));
    }

    @Override
    public ResponseEntity<Boolean> deletePartner(Long partnerId) {
        Partner partnerEntity = getPartnerEntity(partnerId);
        partnerEntity.setDeleted(true);
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<ResPartnerDto> getPartner(Long partnerId) {
        return ResponseEntity.ok(PartnerMapper.partnerToResPartnerDto(getPartnerEntity(partnerId)));
    }

    @Override
    public Partner getPartnerEntity(Long partnerId) {
        Optional<Partner> partnerEntity = findPartnerEntity(partnerId);
        if (partnerEntity.isPresent()){
            return partnerEntity.get();
        }
        throw new EntityNotFoundException("partner entity not found");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Partner> findPartnerEntity(Long partnerId) {
        return partnerRepository.findByIdAndNotDeleted(partnerId);
    }

    @Override
    public ResponseEntity<Page<ResPartnerDto>> getAll() {
        return ResponseEntity.ok(getAllEntity().map(PartnerMapper::partnerToResPartnerDto));
    }

    @Override
    public Page<Partner> getAllEntity() {
        return partnerRepository.findAll(PageRequest.of(0,20));
    }
//    @Override
//    public ResponseEntity<Page<ResPartnerDto>> getAll() {
//        System.out.println(partnerRepository.findAll());
//        return ResponseEntity.ok(null);
//    }
//
//    @Override
//    public Page<Partner> getAllEntity() {
//        System.out.println(partnerRepository.findAll());
//        return null;
//    }
}

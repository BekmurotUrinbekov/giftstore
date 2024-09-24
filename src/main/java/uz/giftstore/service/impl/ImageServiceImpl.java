package uz.giftstore.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.giftstore.dto.res.ResImageDto;
import uz.giftstore.entity.*;
import uz.giftstore.repository.*;
import uz.giftstore.service.ImageService;
import uz.giftstore.service.mapper.ImageMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final AboutUsRepository aboutUsRepository;
    private final ProductRepository productRepository;
    private final PartnerRepository partnerRepository;
    private  final DiscountRepository discountRepository;
    private final ServicesRepository servicesRepository;

    public ImageServiceImpl(ImageRepository imageRepository, AboutUsRepository aboutUsRepository, ProductRepository productRepository, PartnerRepository partnerRepository, DiscountRepository discountRepository, ServicesRepository servicesRepository) {
        this.imageRepository = imageRepository;
        this.aboutUsRepository = aboutUsRepository;
        this.productRepository = productRepository;
        this.partnerRepository = partnerRepository;
        this.discountRepository = discountRepository;
        this.servicesRepository = servicesRepository;
    }

    @Override
    public ResponseEntity<ResImageDto> upload(MultipartFile multipartFile, Map<String,Long> list) {
        Image image=new Image(multipartFile);
        File file=new File(image.getPath());
        file.getParentFile().mkdirs();
        file=file.getAbsoluteFile();
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (list.get("services_id") !=null){
            Optional<Services> services = servicesRepository.findById(list.get("services_id"));
            if (services.isPresent()){
                image.setServices(services.get());
                imageRepository.save(image);
                servicesRepository.save(services.get());
            }
        }
        if (list.get("product_id")!=null){
            Optional<Product> product = productRepository.findById(list.get("product_id"));
            if (product.isPresent()){
                image.setProduct(product.get());
                imageRepository.save(image);
                productRepository.save(product.get());
            }
        }
        if (list.get("partner_id")!=null){
            Optional<Partner> partner = partnerRepository.findById(list.get("partner_id"));
            if (partner.isPresent()){
                image.setPartner(partner.get());
                imageRepository.save(image);
                partnerRepository.save(partner.get());
            }
        }
        if (list.get("discount_id")!=null){
            Optional<Discount> discount = discountRepository.findById(list.get("discount_id"));
            if (discount.isPresent()){
                image.setDiscount(discount.get());
                imageRepository.save(image);
                discountRepository.save(discount.get());
            }
        }
        if (list.get("aboutUs_id")!=null){
            Optional<AboutUs> aboutUs = aboutUsRepository.findById(list.get("aboutUs_id"));
            if (aboutUs.isPresent()){
                aboutUs.get().addImage(image);
                imageRepository.save(image);
                aboutUsRepository.save(aboutUs.get());
            }
        }
        imageRepository.save(image);
        return ResponseEntity.ok(ImageMapper.imageToResImageDto(image));


    }

    @Override
    public ResponseEntity<?> download(String imageId) {
        Optional<Image> image = imageRepository.findByImageId(imageId);
        if (image.isPresent()){
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream(image.get().getPath());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            InputStreamResource resource=new InputStreamResource(inputStream);
            return ResponseEntity.ok()
                    .header("Content-Disposition",("attachment; filename=\""+ image.get().getName()+"\""))
                    .contentType(MediaType.parseMediaType(image.get().getContentType()))
                    .body(resource);

        }
        throw new EntityNotFoundException("image entity not found");
    }

    @Override
    public ResponseEntity<?> preview(String imageId) {
        Optional<Image> image = imageRepository.findByImageId(imageId);
        if (image.isPresent()){
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream(image.get().getPath());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            InputStreamResource resource=new InputStreamResource(inputStream);
            return ResponseEntity.ok()
                    .header("Content-Disposition",("inline; filename=\""+ image.get().getName()+"\""))
                    .contentType(MediaType.parseMediaType(image.get().getContentType()))
                    .body(resource);

        }
        throw new EntityNotFoundException("image entity not found");
    }

    @Override
    public ResponseEntity<ResImageDto> updateImage(String imageId, Map<String,Long> list, MultipartFile multipartFile) {
        Image imageEntity = getImageEntity(imageId);
        if (multipartFile != null){
            Image image=new Image(multipartFile);
            File oldFile=new File(imageEntity.getPath());
            oldFile.delete();
            imageEntity.setContentType(image.getContentType());
            imageEntity.setName(image.getName());
            imageEntity.setPath(image.getPath());
            imageEntity.setSize(image.getSize());
            imageEntity.setType(image.getType());
            File file=new File(image.getPath());
            file.getParentFile().mkdirs();
            file=file.getAbsoluteFile();
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            imageRepository.save(imageEntity);
        }
        if (list.get("services_id") !=null){
            Optional<Services> services = servicesRepository.findById(list.get("services_id"));
            if (services.isPresent()){
                imageEntity.setServices(services.get());
                imageRepository.save(imageEntity);
                servicesRepository.save(services.get());
            }
        }
        if (list.get("product_id") !=null){
            Optional<Product> product = productRepository.findById(list.get("product_id"));
            if (product.isPresent()){
                imageEntity.setProduct(product.get());
                imageRepository.save(imageEntity);
                productRepository.save(product.get());
            }
        }
        if (list.get("partner_id") !=null){
            Optional<Partner> partner = partnerRepository.findById(list.get("partner_id"));
            if (partner.isPresent()){
                imageEntity.setPartner(partner.get());
                imageRepository.save(imageEntity);
                partnerRepository.save(partner.get());
            }
        }
        if (list.get("discount_id") !=null){
            Optional<Discount> discount = discountRepository.findById(list.get("discount_id"));
            if (discount.isPresent()){
                imageEntity.setDiscount(discount.get());
                imageRepository.save(imageEntity);
                discountRepository.save(discount.get());
            }
        }
        if (list.get("aboutUs_id") !=null){
            Optional<AboutUs> aboutUs = aboutUsRepository.findById(list.get("aboutUs_id"));
            if (aboutUs.isPresent()){
                imageEntity.setAboutUs(aboutUs.get());
                imageRepository.save(imageEntity);
                aboutUsRepository.save(aboutUs.get());
            }
        }
        return ResponseEntity.ok(ImageMapper.imageToResImageDto(imageEntity));
    }

    @Override
    public ResponseEntity<Boolean> deleteImage(String imageId) {
        Image imageEntity = getImageEntity(imageId);
        imageEntity.setDeleted(true);
        imageRepository.save(imageEntity);
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<ResImageDto> getImage(String imageId) {
        return ResponseEntity.ok(ImageMapper.imageToResImageDto(getImageEntity(imageId)));
    }

    @Override
    public Image getImageEntity(String imageId) {
        Optional<Image> imageEntity = findImageEntity(imageId);
        if (imageEntity.isPresent()){
            return imageEntity.get();
        }
        throw new EntityNotFoundException("image entity not found");
    }

    @Override
    public Optional<Image> findImageEntity(String imageId) {
        return imageRepository.findByImageId(imageId);
    }

    @Override
    public ResponseEntity<Page<ResImageDto>> getAll() {
        return ResponseEntity.ok(getAllEntity().map(ImageMapper::imageToResImageDto));
    }

    @Override
    public Page<Image> getAllEntity() {
        return imageRepository.findAll(PageRequest.of(0,20));
    }
}

package uz.giftstore.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import uz.giftstore.dto.res.ResImageDto;
import uz.giftstore.entity.Image;

import java.util.Map;
import java.util.Optional;


public interface ImageService {

    ResponseEntity<ResImageDto> upload(MultipartFile multipartFile, Map<String,Long> list);
    ResponseEntity<?> download(String imageId);
    ResponseEntity<?> preview(String imageId);

    ResponseEntity<ResImageDto> updateImage(String imageId, Map<String,Long> list,MultipartFile multipartFile);

    ResponseEntity<Boolean> deleteImage(String imageId);

    ResponseEntity<ResImageDto> getImage(String imageId);

    Image getImageEntity(String imageId);

    Optional<Image> findImageEntity(String imageId);


    ResponseEntity<Page<ResImageDto>> getAll();

    Page<Image> getAllEntity();



}

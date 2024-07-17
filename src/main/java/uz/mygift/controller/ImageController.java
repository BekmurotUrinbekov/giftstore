package uz.mygift.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.mygift.base.BaseUrl;
import uz.mygift.dto.req.ReqImageDto;
import uz.mygift.dto.res.ResImageDto;
import uz.mygift.service.impl.ImageServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(BaseUrl.IMAGE)
@RequiredArgsConstructor
public class ImageController {
    private final ImageServiceImpl image;

    @GetMapping(BaseUrl.GET_ALL)
    public ResponseEntity<Page<ResImageDto>> getAll(){
        return image.getAll();
    }

    @GetMapping(BaseUrl.DELETE_BY_ID)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id){
        return image.deleteImage(id);
    }
    @PostMapping(value = BaseUrl.UPDATE_BY_ID,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResImageDto> updateImage(@PathVariable String id, @RequestParam(value = "aboutUs_id",required = false) Long aboutUs_id,
                                                   @RequestParam(value = "product_id",required = false) Long product_id,
                                                   @RequestParam(value = "partner_id",required = false) Long partner_id,
                                                   @RequestParam(value = "discount_id",required = false) Long discount_id,
                                                   @RequestParam(value = "services_id",required = false) Long services_id,
                                                   @RequestPart(value = "file",required = false) MultipartFile multipartFile){
        Map<String,Long> list=new HashMap<>();
        list.put("aboutUs_id",aboutUs_id);
        list.put("product_id",product_id);
        list.put("partner_id",partner_id);
        list.put("discount_id",discount_id);
        list.put("services_id",services_id);
        return image.updateImage(id,list,multipartFile);
    }

    @PostMapping(value = BaseUrl.CREATE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResImageDto> createImage(@RequestParam(value = "aboutUs_id",required = false) Long aboutUs_id,
                                                   @RequestParam(value = "product_id",required = false) Long product_id,
                                                   @RequestParam(value = "partner_id",required = false) Long partner_id,
                                                   @RequestParam(value = "discount_id",required = false) Long discount_id,
                                                   @RequestParam(value = "services_id",required = false) Long services_id,
                                                   @NotNull @RequestPart(value = "file") MultipartFile multipartFile){
        Map<String,Long> list=new HashMap<>();
        list.put("aboutUs_id",aboutUs_id);
        list.put("product_id",product_id);
        list.put("partner_id",partner_id);
        list.put("discount_id",discount_id);
        list.put("services_id",services_id);
        return image.upload(multipartFile,list);
    }
    @GetMapping(BaseUrl.FIND_BY_ID)
    public ResponseEntity<ResImageDto> findById(@PathVariable("id") String id){
        return image.getImage(id);
    }
    @GetMapping(BaseUrl.DOWNLOAD_BY_ID)
    public ResponseEntity<?> downloadById(@PathVariable("id") String id){
        return image.download(id);
    }
    @GetMapping(BaseUrl.PREVIEW_BY_ID)
    public ResponseEntity<?> previewById(@PathVariable("id") String id){
        return image.preview(id);
    }




}

package uz.giftstore.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.giftstore.base.BaseUrl;
import uz.giftstore.dto.req.ReqServiceTypeDto;
import uz.giftstore.dto.res.ResServiceTypeDto;
import uz.giftstore.service.impl.ServiceTypeServiceImpl;

@RestController
@RequestMapping(BaseUrl.SERVICE_TYPE)
@RequiredArgsConstructor
public class ServiceTypeController {
    private final ServiceTypeServiceImpl serviceTypeService;

    @GetMapping(BaseUrl.GET_ALL)
    public ResponseEntity<Page<ResServiceTypeDto>> getAll(){
        return serviceTypeService.getAll();
    }

    @GetMapping(BaseUrl.DELETE_BY_ID)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
        return serviceTypeService.deleteServicesType(id);
    }
    @PostMapping(BaseUrl.UPDATE_BY_ID)
    public ResponseEntity<ResServiceTypeDto> updateServiceType(@PathVariable Long id, @Valid  @RequestBody ReqServiceTypeDto reqServiceTypeDto){
        return serviceTypeService.updateServiceType(id,reqServiceTypeDto);
    }

    @PostMapping(BaseUrl.CREATE)
    public ResponseEntity<ResServiceTypeDto> createServiceType(@Valid @RequestBody ReqServiceTypeDto reqServiceTypeDto){
        return serviceTypeService.createServicesType(reqServiceTypeDto);
    }
    @GetMapping(BaseUrl.FIND_BY_ID)
    public ResponseEntity<ResServiceTypeDto> findById(@PathVariable("id") Long id){
        return serviceTypeService.getServicesType(id);
    }


}

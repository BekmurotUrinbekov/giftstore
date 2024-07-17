package uz.mygift.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mygift.base.BaseUrl;
import uz.mygift.dto.req.ReqServiceDto;
import uz.mygift.dto.res.ResServiceDto;
import uz.mygift.service.impl.ServicesServImpl;

@RestController
@RequestMapping(BaseUrl.SERVICE)
@RequiredArgsConstructor
public class ServiceController {
    private final ServicesServImpl serviceService;

    @GetMapping(BaseUrl.GET_ALL)
    public ResponseEntity<Page<ResServiceDto>> getAll(){
        return serviceService.getAll();
    }

    @GetMapping(BaseUrl.DELETE_BY_ID)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
        return serviceService.deleteServices(id);
    }
    @PostMapping(BaseUrl.UPDATE_BY_ID)
    public ResponseEntity<ResServiceDto> updateService(@PathVariable Long id, @Valid  @RequestBody ReqServiceDto reqServiceDto){
        return serviceService.updateService(id,reqServiceDto);
    }

    @PostMapping(BaseUrl.CREATE)
    public ResponseEntity<ResServiceDto> createService(@Valid @RequestBody ReqServiceDto reqServiceDto){
        return serviceService.createServices(reqServiceDto);
    }
    @GetMapping(BaseUrl.FIND_BY_ID)
    public ResponseEntity<ResServiceDto> findById(@PathVariable("id") Long id){
        return serviceService.getServices(id);
    }


}

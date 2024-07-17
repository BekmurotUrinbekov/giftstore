package uz.mygift.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mygift.base.BaseUrl;
import uz.mygift.dto.req.ReqPartnerDto;
import uz.mygift.dto.res.ResPartnerDto;
import uz.mygift.dto.upd.UpdatePartnerDto;
import uz.mygift.service.impl.PartnerServiceImpl;

@RestController
@RequestMapping(BaseUrl.PARTNER)
@RequiredArgsConstructor
public class PartnerController {
    private final PartnerServiceImpl partnerPartner;

    @GetMapping(BaseUrl.GET_ALL)
    public ResponseEntity<Page<ResPartnerDto>> getAll(){
        return partnerPartner.getAll();
    }

    @GetMapping(BaseUrl.DELETE_BY_ID)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
        return partnerPartner.deletePartner(id);
    }
    @PostMapping(BaseUrl.UPDATE_BY_ID)
    public ResponseEntity<ResPartnerDto> updatePartner(@PathVariable Long id, @Valid  @RequestBody UpdatePartnerDto updatePartnerDto){
        return partnerPartner.updatePartner(id,updatePartnerDto);
    }

    @PostMapping(BaseUrl.CREATE)
    public ResponseEntity<ResPartnerDto> createPartner(@Valid @RequestBody ReqPartnerDto reqPartnerDto){
        return partnerPartner.createPartner(reqPartnerDto);
    }
    @GetMapping(BaseUrl.FIND_BY_ID)
    public ResponseEntity<ResPartnerDto> findById(@PathVariable("id") Long id){
        return partnerPartner.getPartner(id);
    }


}

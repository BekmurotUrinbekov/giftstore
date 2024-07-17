package uz.mygift.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mygift.base.BaseUrl;
import uz.mygift.dto.req.ReqCategoryDto;
import uz.mygift.dto.res.ResCategoryDto;
import uz.mygift.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping(BaseUrl.CATEGORY)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryCategory;

    @GetMapping(BaseUrl.GET_ALL)
    public ResponseEntity<Page<ResCategoryDto>> getAll(){
        return categoryCategory.getAll();
    }

    @GetMapping(BaseUrl.DELETE_BY_ID)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
        return categoryCategory.deleteCategory(id);
    }
    @PostMapping(BaseUrl.UPDATE_BY_ID)
    public ResponseEntity<ResCategoryDto> updateCategory(@PathVariable Long id, @Valid @RequestBody ReqCategoryDto reqCategoryDto){
        return categoryCategory.updateCategory(id,reqCategoryDto);
    }

    @PostMapping(BaseUrl.CREATE)
    public ResponseEntity<ResCategoryDto> createCategory(@Valid @RequestBody ReqCategoryDto reqCategoryDto){
        return categoryCategory.createCategory(reqCategoryDto);
    }
    @GetMapping(BaseUrl.FIND_BY_ID)
    public ResponseEntity<ResCategoryDto> findById(@PathVariable("id") Long id){
        return categoryCategory.getCategory(id);
    }


}

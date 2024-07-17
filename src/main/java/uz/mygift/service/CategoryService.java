package uz.mygift.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.mygift.dto.req.ReqCategoryDto;
import uz.mygift.dto.res.ResCategoryDto;
import uz.mygift.entity.Category;

import java.util.Optional;


public interface CategoryService {

    ResponseEntity<ResCategoryDto> createCategory(ReqCategoryDto reqCategoryDto);

    ResponseEntity<ResCategoryDto> updateCategory(Long categoryId, ReqCategoryDto reqCategoryDto);

    ResponseEntity<Boolean> deleteCategory(Long categoryId);

    ResponseEntity<ResCategoryDto> getCategory(Long categoryId);

    Category getCategoryEntity(Long categoryId);

    Optional<Category> findCategoryEntity(Long categoryId);


    ResponseEntity<Page<ResCategoryDto>> getAll();

    Page<Category> getAllEntity();



}

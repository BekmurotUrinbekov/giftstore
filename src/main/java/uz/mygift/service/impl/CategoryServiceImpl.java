package uz.mygift.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.mygift.dto.req.ReqCategoryDto;
import uz.mygift.dto.res.ResCategoryDto;
import uz.mygift.entity.Category;
import uz.mygift.repository.CategoryRepository;
import uz.mygift.repository.ProductRepository;
import uz.mygift.service.CategoryService;
import uz.mygift.service.mapper.CategoryMapper;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;



    @Override
    public ResponseEntity<ResCategoryDto> createCategory(ReqCategoryDto reqCategoryDto) {
        Category category = CategoryMapper.reqCategoryDtoToCategory(reqCategoryDto);
        categoryRepository.save(category);
        return ResponseEntity.ok(CategoryMapper.categoryToResCategoryDto(category));
    }

    @Override
    public ResponseEntity<ResCategoryDto> updateCategory(Long categoryId, ReqCategoryDto reqCategoryDto) {
        Category categoryEntity = getCategoryEntity(categoryId);
        if (reqCategoryDto.getCategoryName()!=null){
            categoryEntity.setCategoryName(reqCategoryDto.getCategoryName());
        }
        categoryRepository.save(categoryEntity);
        return ResponseEntity.ok(CategoryMapper.categoryToResCategoryDto(categoryEntity));
    }

    @Override
    public ResponseEntity<Boolean> deleteCategory(Long categoryId) {
        Category categoryEntity = getCategoryEntity(categoryId);
        categoryEntity.setDeleted(true);
        categoryRepository.save(categoryEntity);
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<ResCategoryDto> getCategory(Long categoryId) {
        return ResponseEntity.ok(CategoryMapper.categoryToResCategoryDto(getCategoryEntity(categoryId)));
    }

    @Override
    public Category getCategoryEntity(Long categoryId) {
        Optional<Category> categoryEntity = findCategoryEntity(categoryId);
        if (categoryEntity.isPresent()){
            return categoryEntity.get();
        }
        throw new EntityNotFoundException("category entity not found");
    }

    @Override
    public Optional<Category> findCategoryEntity(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public ResponseEntity<Page<ResCategoryDto>> getAll() {
        return ResponseEntity.ok(getAllEntity().map(CategoryMapper::categoryToResCategoryDto));
    }

    @Override
    public Page<Category> getAllEntity() {
        return categoryRepository.findAll(PageRequest.of(0,20));
    }
}

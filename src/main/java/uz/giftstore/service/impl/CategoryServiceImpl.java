package uz.giftstore.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.giftstore.dto.req.ReqCategoryDto;
import uz.giftstore.dto.res.ResCategoryDto;
import uz.giftstore.entity.Category;
import uz.giftstore.repository.CategoryRepository;
import uz.giftstore.service.CategoryService;
import uz.giftstore.service.mapper.CategoryMapper;

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

package uz.mygift.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.mygift.dto.req.ReqDiscountDto;
import uz.mygift.dto.upd.UpdateDiscountDto;
import uz.mygift.dto.res.ResDiscountDto;
import uz.mygift.entity.Discount;
import uz.mygift.entity.Image;
import uz.mygift.entity.Product;
import uz.mygift.repository.DiscountRepository;
import uz.mygift.repository.ImageRepository;
import uz.mygift.repository.ProductRepository;
import uz.mygift.service.DiscountService;
import uz.mygift.service.mapper.DiscountMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    @Override
    public ResponseEntity<ResDiscountDto> createDiscount(ReqDiscountDto reqDiscountDto) {
        Optional<Product> product = productRepository.findById(reqDiscountDto.getProduct_id());
        if (product.isPresent()){
            Optional<Image> image = imageRepository.findById(reqDiscountDto.getImage_id());
            Image imageEntity;
            if (image.isPresent()){
                imageEntity=image.get();
            }else {
                imageEntity=product.get().getImage();
            }
            Discount discount = DiscountMapper.reqDiscountDtoToDiscount(reqDiscountDto);
            discount.setImage(imageEntity);
            discount.setProduct(product.get());
            discountRepository.save(discount);
            product.get().setDiscount(discount);
            productRepository.save(product.get());
            imageEntity.setDiscount(discount);
            imageRepository.save(imageEntity);
            return ResponseEntity.ok(DiscountMapper.discountToResDiscountDto(discount));
        }
        throw new EntityNotFoundException("product entity not found");
    }

    @Override
    public ResponseEntity<ResDiscountDto> updateDiscount(Long discountId, UpdateDiscountDto reqDiscountUpdateDto) {
        Discount discountEntity = getDiscountEntity(discountId);
        if (reqDiscountUpdateDto.getDiscountPrice() != null) {
            discountEntity.setDiscountPrice(reqDiscountUpdateDto.getDiscountPrice());
        }
        if (reqDiscountUpdateDto.getImage_id() != null){
            Optional<Image> image = imageRepository.findById(reqDiscountUpdateDto.getImage_id());
            if (image.isPresent()) {
                discountEntity.setImage(image.get());
            }
        }
        if (reqDiscountUpdateDto.getProduct_id() != null){
            Optional<Product> product = productRepository.findById(reqDiscountUpdateDto.getProduct_id());
            if (product.isPresent()) {
                discountEntity.setProduct(product.get());
            }
        }
        if (reqDiscountUpdateDto.getDeadline() != null){
            discountEntity.setDeadline(reqDiscountUpdateDto.getDeadline());
        }
        discountRepository.save(discountEntity);
        return ResponseEntity.ok(DiscountMapper.discountToResDiscountDto(discountEntity));
    }

    @Override
    public ResponseEntity<Boolean> deleteDiscount(Long discountId) {
        Discount discountEntity = getDiscountEntity(discountId);
        discountEntity.setIsDiscount(true);
        discountRepository.save(discountEntity);
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<ResDiscountDto> getDiscount(Long discountId) {
        return ResponseEntity.ok(DiscountMapper.discountToResDiscountDto(getDiscountEntity(discountId)));
    }

    @Override
    public Discount getDiscountEntity(Long discountId) {
        Optional<Discount> discountEntity = findDiscountEntity(discountId);
        if (discountEntity.isPresent()){
            return discountEntity.get();
        }
        throw new EntityNotFoundException("discount entity not found");
    }

    @Override
    public Optional<Discount> findDiscountEntity(Long discountId) {
        return discountRepository.findById(discountId);
    }

    @Override
    public ResponseEntity<Page<ResDiscountDto>> getAll() {
        return ResponseEntity.ok(getAllEntity().map(DiscountMapper::discountToResDiscountDto));
    }

    @Override
    public Page<Discount> getAllEntity() {
        return discountRepository.findAll(PageRequest.of(0,20));
    }
}

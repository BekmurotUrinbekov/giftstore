package uz.giftstore.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.giftstore.dto.req.ReqProductDto;
import uz.giftstore.dto.res.*;
import uz.giftstore.dto.upd.UpdateProductDto;
import uz.giftstore.entity.*;
import uz.giftstore.enums.Gender;
import uz.giftstore.repository.*;
import uz.giftstore.service.ProductService;
import uz.giftstore.service.mapper.ProductMapper;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final DiscountRepository discountRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final  BasketRepository basketRepository;
    private final FavoriteRepository favoriteRepository;
    private final ViewsRepository viewsRepository;
    private volatile static String article;
    private static AtomicInteger articleNumber = new AtomicInteger(1000);

    public ProductServiceImpl(ProductRepository productRepository, ProductTypeRepository productTypeRepository, DiscountRepository discountRepository, CategoryRepository categoryRepository, ImageRepository imageRepository, BasketRepository basketRepository, FavoriteRepository favoriteRepository, ViewsRepository viewsRepository) {
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
        this.discountRepository = discountRepository;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
        this.basketRepository = basketRepository;
        this.favoriteRepository = favoriteRepository;
        this.viewsRepository = viewsRepository;
    }
    private static String generateArticle(){
        String prefix="ART ";
        int suffix = articleNumber.getAndIncrement();
        article = prefix+suffix;
        return article;
    }

    @Override
    public ResponseEntity<ResProductDto> createProduct(ReqProductDto reqProductDto) {
        Optional<ProductType> productType = productTypeRepository.findById(reqProductDto.getProductType_id());
        Optional<Category> category = categoryRepository.findById(reqProductDto.getCategory_id());
        Optional<Image> image = imageRepository.findById(reqProductDto.getImage_id());
        if (productType.isPresent() && category.isPresent() && image.isPresent()){
            Product product = ProductMapper.reqProductDtoToProduct(reqProductDto);
            product.setArticle(generateArticle());
            product.setProductType(productType.get());
            product.setCategory(category.get());
            product.setImage(image.get());


//            if (reqProductDto.getBasket_id() != null){
//                Optional<Basket> basket = basketRepository.findById(reqProductDto.getBasket_id());
//                if (basket.isPresent()){
//                    product.setBasket(basket.get());
//                    productRepository.save(product);
//                    basket.get().setProduct(product);
//                    basketRepository.save(basket.get());
//                }
//            }
            if (reqProductDto.getDiscount_id() != null){
                Optional<Discount> discount = discountRepository.findById(reqProductDto.getDiscount_id());
                if (discount.isPresent()){
                    product.setDiscount(discount.get());
                    productRepository.save(product);
                    discount.get().setProduct(product);
                    discountRepository.save(discount.get());
                }
            }
//            if (reqProductDto.getViews_id() != null){
//                Optional<Views> views = viewsRepository.findById(reqProductDto.getViews_id());
//                if (views.isPresent()){
//                    product.setViews(views.get());
//                    productRepository.save(product);
//                    views.get().setProduct(product);
//                    viewsRepository.save(views.get());
//                }
//            }
//            if (reqProductDto.getFavorite_id() != null){
//                Optional<Favorite> favorite = favoriteRepository.findById(reqProductDto.getFavorite_id());
//                if (favorite.isPresent()){
//                    product.setFavorite(favorite.get());
//                    productRepository.save(product);
//                    favorite.get().setProduct(product);
//                    favoriteRepository.save(favorite.get());
//                }
//            }
            productRepository.save(product);
            productType.get().addProduct(product);
            productTypeRepository.save(productType.get());
            image.get().setProduct(product);
            imageRepository.save(image.get());
            category.get().addProduct(product);
            categoryRepository.save(category.get());
            return ResponseEntity.ok(ProductMapper.productToResProductDto(product));
        }
        throw new EntityNotFoundException("entity not found");
    }

    @Override
    public ResponseEntity<ResProductDto> updateProduct(Long productId, UpdateProductDto updateProductDto) {
        Product productEntity = getProductEntity(productId);
        if (updateProductDto.getFullName()!= null){
            productEntity.setFullName(updateProductDto.getFullName());
        }
        if (updateProductDto.getGender() != null){
            productEntity.setGender(Gender.valueOf(updateProductDto.getGender()));
        }
        if (updateProductDto.getProductDescription() != null){
            productEntity.setProductDescription(updateProductDto.getProductDescription());
        }
        if (updateProductDto.getProductName() != null){
            productEntity.setProductName(updateProductDto.getProductName());
        }
        if (updateProductDto.getProductPrice() != null){
            productEntity.setProductPrice(updateProductDto.getProductPrice());
        }
        if (updateProductDto.getProductType_id() != null){
            Optional<ProductType> productType = productTypeRepository.findById(updateProductDto.getProductType_id());
            if (productType.isPresent()){
                productEntity.getProductType().removeProduct(productEntity);
                productTypeRepository.save(productEntity.getProductType());
                productEntity.setProductType(productType.get());
                productRepository.save(productEntity);
                productType.get().addProduct(productEntity);
                productTypeRepository.save(productType.get());
            }
        }
        if (updateProductDto.getCategory_id() != null){
            Optional<Category> category = categoryRepository.findById(updateProductDto.getCategory_id());
            if (category.isPresent()){
                productEntity.getCategory().removeProduct(productEntity);
                categoryRepository.save(productEntity.getCategory());
                productEntity.setCategory(category.get());
                productRepository.save(productEntity);
                category.get().addProduct(productEntity);
                categoryRepository.save(category.get());
            }
        }
//        if (updateProductDto.getBasket_id() != null){
//            Optional<Basket> basket = basketRepository.findById(updateProductDto.getBasket_id());
//            if (basket.isPresent()){
//                productEntity.setBasket(basket.get());
//                productRepository.save(productEntity);
//                basket.get().setProduct(productEntity);
//                basketRepository.save(basket.get());
//            }
//        }
        if (updateProductDto.getImage_id() != null){
            Optional<Image> image = imageRepository.findById(updateProductDto.getImage_id());
            if (image.isPresent()){
                productEntity.setImage(image.get());
                productRepository.save(productEntity);
                image.get().setProduct(productEntity);
                imageRepository.save(image.get());
            }
        }
//        if (updateProductDto.getViews_id() != null){
//            Optional<Views> views = viewsRepository.findById(updateProductDto.getViews_id());
//            if (views.isPresent()){
//                productEntity.setViews(views.get());
//                productRepository.save(productEntity);
//                views.get().setProduct(productEntity);
//                viewsRepository.save(views.get());
//            }
//        }
//        if (updateProductDto.getFavorite_id() != null){
//            Optional<Favorite> favorite = favoriteRepository.findById(updateProductDto.getFavorite_id());
//            if (favorite.isPresent()){
//                productEntity.setFavorite(favorite.get());
//                productRepository.save(productEntity);
//                favorite.get().setProduct(productEntity);
//                favoriteRepository.save(favorite.get());
//            }
//        }
        if (updateProductDto.getDiscount_id() != null){
            Optional<Discount> discount = discountRepository.findById(updateProductDto.getDiscount_id());
            if (discount.isPresent()){
                productEntity.setDiscount(discount.get());
                productRepository.save(productEntity);
                discount.get().setProduct(productEntity);
                discountRepository.save(discount.get());
            }
        }
        productRepository.save(productEntity);
        return ResponseEntity.ok(ProductMapper.productToResProductDto(productEntity));
    }

    @Override
    public ResponseEntity<Boolean> deleteProduct(Long productId) {
        Product productEntity = getProductEntity(productId);
        productEntity.setDeleted(true);
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<ResProductDto> getProduct(Long productId) {
        return ResponseEntity.ok(ProductMapper.productToResProductDto(getProductEntity(productId)));
    }

    @Override
    public Product getProductEntity(Long productId) {
        Optional<Product> productEntity = findProductEntity(productId);
        if (productEntity.isPresent()){
            return productEntity.get();
        }
        throw new EntityNotFoundException("product entity not found");
    }

    @Override
    public Optional<Product> findProductEntity(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public ResponseEntity<Page<ResProductDto>> getAll() {
        return ResponseEntity.ok(getAllEntity().map(ProductMapper::productToResProductDto));
    }

    @Override
    public Page<Product> getAllEntity() {
        return productRepository.findAll(PageRequest.of(0,20));
    }
}

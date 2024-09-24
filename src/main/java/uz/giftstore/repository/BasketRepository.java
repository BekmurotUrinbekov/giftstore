package uz.giftstore.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.giftstore.base.BaseRepository;
import uz.giftstore.entity.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BasketRepository extends BaseRepository<Basket> {
    @Query("SELECT b.product, COUNT(b) as basketCount FROM Basket b GROUP BY b.product ORDER BY basketCount DESC")
    List<Object[]> findMostBaskedProducts(Pageable pageable);

    List<Basket> findBySessionEntity_User_Id(Long userId);
    List<Basket> findBasketBySessionEntity(SessionEntity sessionEntity);

    Optional<Basket> findBySessionEntityAndProduct(SessionEntity sessionEntity, Product product);

    @Query("SELECT b.product, COUNT(b) as basCount " +
            "FROM Basket b " +
            "WHERE b.isBasket = true AND b.updatedAt >= :startDate " +
            "GROUP BY b.product " +
            "ORDER BY basCount DESC")
    List<Object[]> findMostBasketProducts(@Param("startDate") LocalDateTime startDate, Pageable pageable);

    @Query("SELECT COUNT(b) as basketCount " +
            "FROM Basket b " +
            "WHERE b.product.id = :productId")
    Optional<Long> countBasketsByProductId(@Param("productId") Long productId);
}

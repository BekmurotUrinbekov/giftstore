package uz.mygift.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.mygift.base.BaseRepository;
import uz.mygift.entity.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ViewsRepository extends BaseRepository<Views> {

    @Query("SELECT v.product, COUNT(v) as viewCount FROM Views v GROUP BY v.product ORDER BY viewCount DESC")
    List<Object[]> findMostViewedProducts(Pageable pageable);

    List<Views> findBySessionEntity_User_Id(Long userId);


    Optional<Views> findBySessionEntityAndProduct(SessionEntity sessionEntity, Product product);
    @Query("SELECT v.product, COUNT(v) as viewsCount " +
            "FROM Views v " +
            "WHERE v.isViewed = true AND v.updatedAt >= :startDate " +
            "GROUP BY v.product " +
            "ORDER BY viewsCount DESC")
    List<Object[]> findMostViewsProducts(@Param("startDate") LocalDateTime startDate, Pageable pageable);
    @Query("SELECT COUNT(v) as viewsCount " +
            "FROM Views v " +
            "WHERE v.product.id = :productId")
    Optional<Long> countViewsByProductId(@Param("productId") Long productId);

}

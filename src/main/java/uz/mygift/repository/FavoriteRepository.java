package uz.mygift.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.mygift.base.BaseRepository;
import uz.mygift.entity.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends BaseRepository<Favorite> {
    List<Favorite> findBySessionEntity_User_Id(Long userId);


    //    List<Basket> findByUser(User user);
    Optional<Favorite> findBySessionEntityAndProduct(SessionEntity sessionEntity, Product product);
    @Query("SELECT f.product, COUNT(f) as favoriteCount FROM Favorite f GROUP BY f.product ORDER BY favoriteCount DESC")
    List<Object[]> findMostFavoritedProducts(Pageable pageable);

    @Query("SELECT f.product, COUNT(f) as favCount " +
            "FROM Favorite f " +
            "WHERE f.isFavorite = true AND f.updatedAt >= :startDate " +
            "GROUP BY f.product " +
            "ORDER BY favCount DESC")
    List<Object[]> findMostFavoriteProducts(@Param("startDate") LocalDateTime startDate, Pageable pageable);

}

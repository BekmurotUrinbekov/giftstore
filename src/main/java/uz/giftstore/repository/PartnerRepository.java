package uz.giftstore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.giftstore.base.BaseRepository;
import uz.giftstore.entity.Partner;

import java.util.Optional;

public interface PartnerRepository extends BaseRepository<Partner> {
    @Query("SELECT p FROM Partner p WHERE p.id = :id AND p.deleted = false")
    Optional<Partner> findByIdAndNotDeleted(@Param("id") Long id);
}

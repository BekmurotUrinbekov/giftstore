package uz.mygift.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.mygift.base.BaseRepository;
import uz.mygift.entity.Partner;

import java.util.Optional;

public interface PartnerRepository extends BaseRepository<Partner> {
    @Query("SELECT p FROM Partner p WHERE p.id = :id AND p.deleted = false")
    Optional<Partner> findByIdAndNotDeleted(@Param("id") Long id);
}

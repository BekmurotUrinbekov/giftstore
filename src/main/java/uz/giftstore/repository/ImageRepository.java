package uz.giftstore.repository;

import uz.giftstore.base.BaseRepository;
import uz.giftstore.entity.Image;

import java.util.Optional;

public interface ImageRepository extends BaseRepository<Image> {
    Optional<Image> findByImageId(String imageId);
}

package uz.mygift.repository;

import uz.mygift.base.BaseRepository;
import uz.mygift.entity.Image;

import java.util.Optional;

public interface ImageRepository extends BaseRepository<Image> {
    Optional<Image> findByImageId(String imageId);
}

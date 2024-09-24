package uz.giftstore.repository;


import org.springframework.stereotype.Repository;
import uz.giftstore.base.BaseRepository;
import uz.giftstore.entity.User;

import java.util.Optional;
@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByUserName(String userName);

}

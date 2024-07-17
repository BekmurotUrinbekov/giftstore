package uz.mygift.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mygift.base.BaseRepository;
import uz.mygift.entity.User;

import java.util.Optional;
@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByUserName(String userName);

}

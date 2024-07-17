package uz.mygift.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uz.mygift.entity.User;
import uz.mygift.dto.req.ReqUserDto;
import uz.mygift.dto.res.ResUserDto;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface UserService  {
    ResponseEntity<Map<String,String>> generateToken(String userName);
    ResponseEntity<Map<String,String>> refreshToken(String token);

    ResponseEntity<ResUserDto> createUser(ReqUserDto userDto);

    ResponseEntity<ResUserDto> updateUser(Long userId,ReqUserDto userDto);

    ResponseEntity<Boolean> deleteUser(Long userId);

    ResponseEntity<ResUserDto> getUser(Long userId);

    User getUserEntity(Long userId);

    Optional<User> findUserEntity(Long userId);


    ResponseEntity<Page<ResUserDto>> getAll();

    Page<User> getAllEntity();



}

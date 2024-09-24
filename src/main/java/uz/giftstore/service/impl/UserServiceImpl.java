package uz.giftstore.service.impl;


import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uz.giftstore.dto.req.ReqUserDto;
import uz.giftstore.dto.res.ResUserDto;
import uz.giftstore.entity.User;
import uz.giftstore.enums.UserRole;
import uz.giftstore.repository.UserRepository;
import uz.giftstore.service.UserDetService;
import uz.giftstore.service.UserService;
import uz.giftstore.service.mapper.UserMapper;
import uz.giftstore.utils.JwtTokenUteils;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenUteils jwtTokenUteils;
    private final UserDetService userDetService;
    private static int beginNumber=0;
    private static int sizeNumber=0;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtTokenUteils jwtTokenUteils, UserDetService userDetService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenUteils = jwtTokenUteils;
        this.userDetService = userDetService;
    }

    @Override
    public ResponseEntity<Map<String, String>> generateToken(String userName) {
        String token = jwtTokenUteils.generateToken(userDetService.loadUserByUsername(userName));
        String refreshToken = jwtTokenUteils.refreshToken(token);
        Map<String,String> list=new HashMap<>();
        list.put("Authorization","Bearer "+token);
        list.put("RefreshToken","Bearer "+refreshToken);
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<Map<String, String>> refreshToken(String token) {
        String newToken = jwtTokenUteils.generateTokenWithRefreshToken(token);
        String refreshToken = jwtTokenUteils.refreshToken(newToken);
        Map<String,String> list=new HashMap<>();
        list.put("Authorization","Bearer "+newToken);
        list.put("RefreshToken","Bearer "+refreshToken);
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<ResUserDto> createUser(ReqUserDto userDto) {
        Optional<User> byUserName = userRepository.findByUserName(userDto.getUserName());
        System.out.println(userDto);
        if (!byUserName.isPresent() && !getAllEntity().stream().anyMatch(e->bCryptPasswordEncoder.matches(e.getPassword(),userDto.getPassword()))){
            User user = UserMapper.reqUserDtoToUser(userDto);
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            userRepository.save(user);
            return ResponseEntity.ok(UserMapper.userToResUserDto(user));
        }
        throw new EntityExistsException("Username or password already exists");
    }

    @Override
    public ResponseEntity<ResUserDto> updateUser(Long userId,ReqUserDto userDto) {
        User userEntity = getUserEntity(userId);
        if (!(userDto.getUserName() == null)){
            userEntity.setUserName(userDto.getUserName());
        }
        if (!(userDto.getPassword() == null)){
            userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        }
        if (!(userDto.getUserRole() == null)){
            userEntity.setUserRole(UserRole.valueOf(userDto.getUserRole()));
        }
        userRepository.save(userEntity);
        return ResponseEntity.ok(UserMapper.userToResUserDto(userEntity));
    }

    @Override
    public ResponseEntity<Boolean> deleteUser(Long userId) {
        User userEntity = getUserEntity(userId);
        userEntity.setDeleted(true);
        userRepository.save(userEntity);
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<ResUserDto> getUser(Long userId) {
        return ResponseEntity.ok(UserMapper.userToResUserDto(getUserEntity(userId)));
    }

    @Override
    public User getUserEntity(Long userId) {
        Optional<User> userEntity = findUserEntity(userId);
        if (userEntity.isPresent()){
            return userEntity.get();
        }
        throw new EntityNotFoundException("User not found");
    }

    @Override
    public Optional<User> findUserEntity(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public ResponseEntity<Page<ResUserDto>> getAll() {
        return ResponseEntity.ok(getAllEntity().map(UserMapper::userToResUserDto));
    }

    @Override
    public Page<User> getAllEntity() {
        sizeNumber +=10;
        return userRepository.findAll(PageRequest.of(beginNumber,sizeNumber));
    }

}

package uz.giftstore.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.giftstore.base.BaseUrl;
import uz.giftstore.dto.req.ReqUserDto;
import uz.giftstore.dto.res.ResUserDto;
import uz.giftstore.service.UserService;

import java.util.Map;


@RestController
@RequestMapping(BaseUrl.USER)
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(BaseUrl.GENERATE_TOKEN)
    ResponseEntity<Map<String,String>> generateToken(@RequestParam("username") String username){return userService.generateToken(username);};

    @PostMapping(BaseUrl.CREATE)
    ResponseEntity<ResUserDto> createUser(@RequestBody @Valid ReqUserDto userDto){return userService.createUser(userDto);};

    @PostMapping(BaseUrl.UPDATE_BY_ID)
    ResponseEntity<ResUserDto> editUser(@RequestBody @Valid ReqUserDto userDto, @PathVariable("id") Long id){
        return userService.updateUser(id,userDto);
    };

    @GetMapping(BaseUrl.DELETE_BY_ID)
    ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long userId){
        return userService.deleteUser(userId);
    };

    @GetMapping(BaseUrl.FIND_BY_ID)
    ResponseEntity<ResUserDto> getUser(@PathVariable("id") Long userId){
        return userService.getUser(userId);
    };

    @GetMapping(BaseUrl.REFRESH_TOKEN)
    ResponseEntity<Map<String,String>> generateRefreshToken(@RequestParam("token") String token){
        return userService.refreshToken(token);};

    @GetMapping(BaseUrl.GET_ALL)
    ResponseEntity<Page<ResUserDto>> getAll(){
        return userService.getAll();
    };


}

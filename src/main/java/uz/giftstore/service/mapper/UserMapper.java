package uz.giftstore.service.mapper;

import uz.giftstore.dto.req.ReqUserDto;
import uz.giftstore.dto.res.ResUserDto;
import uz.giftstore.entity.User;
import uz.giftstore.enums.UserRole;
public class UserMapper {
    public static ResUserDto userToResUserDto(User user) {
        if (user == null) {
            return null;
        }

        ResUserDto resUserDto = new ResUserDto();
        resUserDto.setUserName(user.getUserName());
        resUserDto.setUserRole(user.getUserRole().name());

        return resUserDto;
    }

    public static User reqUserDtoToUser(ReqUserDto reqUserDto) {
        if (reqUserDto == null) {
            return null;
        }

        User user = new User();
        user.setUserName(reqUserDto.getUserName());
        user.setPassword(reqUserDto.getPassword());
        user.setUserRole(UserRole.valueOf(reqUserDto.getUserRole()));

        return user;
    }
}

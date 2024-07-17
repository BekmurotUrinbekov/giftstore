package uz.mygift.service.mapper;

import uz.mygift.dto.req.ReqUserDto;
import uz.mygift.dto.res.ResUserDto;
import uz.mygift.entity.User;
import uz.mygift.enums.UserRole;
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

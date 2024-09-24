package uz.giftstore.service.impl;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.giftstore.entity.User;
import uz.giftstore.repository.UserRepository;
import uz.giftstore.service.UserDetService;


@Service
public class UserDetServiceImpl implements UserDetService {
    private final UserRepository userRepository;

    public UserDetServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(username).orElse(null);
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                user.getUserRole().getGrantedAuthority()
        );
    }
}

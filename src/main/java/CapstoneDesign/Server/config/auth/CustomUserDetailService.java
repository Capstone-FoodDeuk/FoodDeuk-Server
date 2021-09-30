package CapstoneDesign.Server.config.auth;

import CapstoneDesign.Server.domain.entity.user.User;
import CapstoneDesign.Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository<User> userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User findUser = userRepository.findUserByLoginId(loginId);
        if (findUser == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        return findUser;
    }
}
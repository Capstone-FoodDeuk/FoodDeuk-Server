package CapstoneDesign.Server.controller;

import CapstoneDesign.Server.config.auth.JwtTokenProvider;
import CapstoneDesign.Server.domain.dto.ApiResponse;
import CapstoneDesign.Server.domain.dto.UserLoginDTO;
import CapstoneDesign.Server.domain.dto.UserSaveDTO;
import CapstoneDesign.Server.domain.entity.user.GuestUser;
import CapstoneDesign.Server.domain.entity.user.OwnerUser;
import CapstoneDesign.Server.domain.entity.user.User;
import CapstoneDesign.Server.domain.entity.user.UserRole;
import CapstoneDesign.Server.exception.*;
import CapstoneDesign.Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository<User> userRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ApiResponse join(@RequestBody UserSaveDTO user) {

        UserRole role = user.getRole();
        if (role != UserRole.ROLE_GUEST && role != UserRole.ROLE_OWNER) {
            throw new InvalidUserRoleException("유효하지 않은 유저 타입입니다.");
        }
        User findUser = userRepository.findUserByLoginId(user.getLoginId());
        if (findUser != null) {
            throw new LoginIdDuplicatedException("이미 존재하는 아이디입니다.");
        }
        if (!user.getPwd().equals(user.getPwdCheck())) {
            throw new FailedToPwdCheckException("비밀번호가 일치하지 않습니다.");
        }

        if (role == UserRole.ROLE_OWNER) {
            OwnerUser ownerUser = OwnerUser.builder()
                    .loginId(user.getLoginId())
                    .password(passwordEncoder.encode(user.getPwd()))
                    .nickname(user.getNickname())
                    .phoneNumber(user.getPhoneNumber())
                    .build();
            userRepository.save(ownerUser);
            return new ApiResponse(HttpStatus.CREATED, "사장님 유저 회원가입 성공", null);
        }

        GuestUser guestUser = GuestUser.builder()
                .loginId(user.getLoginId())
                .password(passwordEncoder.encode(user.getPwd()))
                .nickname(user.getNickname())
                .phoneNumber(user.getPhoneNumber())
                .build();
        userRepository.save(guestUser);
        return new ApiResponse(HttpStatus.CREATED, "손님 유저 회원가입 성공", null);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public ApiResponse login(@RequestBody UserLoginDTO user) {

        User findUser = userRepository.findUserByLoginId(user.getLoginId());
        if (findUser == null) {
            throw new UserNotFoundException("해당 유저가 존재하지 않습니다.");
        }
        if (!passwordEncoder.matches(user.getPassword(), findUser.getPassword())) {
            throw new FailedToLoginException("잘못된 비밀번호입니다.");
        }
        String token = jwtTokenProvider.createToken(findUser.getId(), findUser.getDTYPE());
        return new ApiResponse(HttpStatus.OK, "로그인 성공", token);
    }
}

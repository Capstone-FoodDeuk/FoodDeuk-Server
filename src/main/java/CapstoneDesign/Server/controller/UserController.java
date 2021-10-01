package CapstoneDesign.Server.controller;

import CapstoneDesign.Server.config.auth.JwtTokenProvider;
import CapstoneDesign.Server.domain.dto.*;
import CapstoneDesign.Server.domain.entity.user.GuestUser;
import CapstoneDesign.Server.domain.entity.user.OwnerUser;
import CapstoneDesign.Server.domain.entity.user.User;
import CapstoneDesign.Server.domain.entity.user.UserRole;
import CapstoneDesign.Server.exception.*;
import CapstoneDesign.Server.repository.UserRepository;
import CapstoneDesign.Server.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    // 해당 컨트롤러에서는 회원가입, 로그인, 홈 화면만 처리한다. 이외 작업들은 Guest, Owner 각각에서 처리.

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository<User> userRepository;
    private final StoreService storeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public ApiResponse home(@RequestBody HomeRequestDTO location) {

        List<HomeStoreDTO> result = storeService.getNearStore(location.getLatitude(), location.getLongitude());
        return new ApiResponse(HttpStatus.OK, "지도화면 푸드트럭 데이터 조회 성공", result);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ApiResponse join(@RequestBody UserSaveDTO user) {

        UserRole role = user.getRole();
        if (role != UserRole.GUEST && role != UserRole.OWNER) {
            throw new InvalidUserRoleException("유효하지 않은 유저 타입입니다.");
        }
        User findUser = userRepository.findUserByLoginId(user.getLoginId());
        if (findUser != null) {
            throw new DuplicatedLoginIdException("이미 존재하는 아이디입니다.");
        }
        if (!user.getPwd().equals(user.getPwdCheck())) {
            throw new FailedToPwdCheckException("비밀번호가 일치하지 않습니다.");
        }

        if (role == UserRole.OWNER) {
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
            throw new NotFoundUserException("해당 유저가 존재하지 않습니다.");
        }
        if (!passwordEncoder.matches(user.getPassword(), findUser.getPassword())) {
            throw new FailedToLoginException("잘못된 비밀번호입니다.");
        }
        String token = jwtTokenProvider.createToken(findUser.getLoginId(), findUser.getDTYPE());
        return new ApiResponse(HttpStatus.OK, "로그인 성공", token);
    }
}

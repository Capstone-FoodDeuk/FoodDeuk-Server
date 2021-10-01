package CapstoneDesign.Server.controller;

import CapstoneDesign.Server.config.annotation.TokenUser;
import CapstoneDesign.Server.domain.dto.ApiResponse;
import CapstoneDesign.Server.domain.dto.UserPageDTO;
import CapstoneDesign.Server.domain.dto.UserPageReviewDTO;
import CapstoneDesign.Server.domain.dto.UserPageZzimDTO;
import CapstoneDesign.Server.domain.entity.user.GuestUser;
import CapstoneDesign.Server.domain.entity.user.User;
import CapstoneDesign.Server.repository.ReviewRepository;
import CapstoneDesign.Server.repository.ZzimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guest")
public class GuestController {

    private final ZzimRepository zzimRepository;
    private final ReviewRepository reviewRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public ApiResponse getGuestUserPage(@TokenUser User user) {
        GuestUser guestUser = (GuestUser) user;

        List<UserPageZzimDTO> zzims = zzimRepository.findZzimsByUserId(guestUser.getId());
        List<UserPageReviewDTO> myReviews = reviewRepository.findMyReviewsByUserId(guestUser.getId());

        UserPageDTO result = new UserPageDTO(guestUser.getNickname(), zzims, myReviews);

        return new ApiResponse(HttpStatus.OK, "유저페이지 조회 성공", result);
    }

}

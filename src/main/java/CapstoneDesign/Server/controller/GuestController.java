package CapstoneDesign.Server.controller;

import CapstoneDesign.Server.config.resolver.TokenUser;
import CapstoneDesign.Server.domain.dto.ApiResponse;
import CapstoneDesign.Server.domain.dto.UserPageDTO;
import CapstoneDesign.Server.domain.dto.UserPageReviewDTO;
import CapstoneDesign.Server.domain.dto.UserPageZzimDTO;
import CapstoneDesign.Server.domain.entity.store.Store;
import CapstoneDesign.Server.domain.entity.user.GuestUser;
import CapstoneDesign.Server.domain.entity.user.User;
import CapstoneDesign.Server.exception.NotFoundStoreException;
import CapstoneDesign.Server.repository.ReviewRepository;
import CapstoneDesign.Server.repository.StoreRepository;
import CapstoneDesign.Server.repository.ZzimRepository;
import CapstoneDesign.Server.service.ZzimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guest")
public class GuestController {

    private final ZzimRepository zzimRepository;
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final ZzimService zzimService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public ApiResponse getGuestUserPage(@TokenUser User user) {
        GuestUser guestUser = (GuestUser) user;

        List<UserPageZzimDTO> zzims = zzimRepository.findZzimsByUserId(guestUser.getId());
        List<UserPageReviewDTO> myReviews = reviewRepository.findMyReviewsByUserId(guestUser.getId());

        UserPageDTO result = new UserPageDTO(guestUser.getNickname(), zzims, myReviews);

        return new ApiResponse(HttpStatus.OK, "유저페이지 조회 성공", result);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/zzim/{storeId}")
    public ApiResponse zzimStore(@PathVariable Long storeId, @TokenUser User user) {

        Store findStore = storeRepository.findById(storeId).orElseThrow(() -> new NotFoundStoreException());
        GuestUser guest = (GuestUser) user;

        zzimService.zzimStore(guest, findStore);
        return new ApiResponse(HttpStatus.OK, "푸드트럭 찜 추가/삭제 성공", null);
    }

}

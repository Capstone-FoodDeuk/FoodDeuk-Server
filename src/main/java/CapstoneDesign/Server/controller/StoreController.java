package CapstoneDesign.Server.controller;

import CapstoneDesign.Server.config.resolver.TokenUser;
import CapstoneDesign.Server.domain.dto.ApiResponse;
import CapstoneDesign.Server.domain.dto.StoreDetailDTO;
import CapstoneDesign.Server.domain.dto.StoreDetailMenuDTO;
import CapstoneDesign.Server.domain.dto.StoreDetailReviewDTO;
import CapstoneDesign.Server.domain.entity.store.PaymentMethod;
import CapstoneDesign.Server.domain.entity.store.Store;
import CapstoneDesign.Server.domain.entity.user.GuestUser;
import CapstoneDesign.Server.domain.entity.user.OwnerUser;
import CapstoneDesign.Server.domain.entity.user.User;
import CapstoneDesign.Server.exception.NotFoundStoreException;
import CapstoneDesign.Server.repository.MenuRepository;
import CapstoneDesign.Server.repository.PaymentRepository;
import CapstoneDesign.Server.repository.StoreRepository;
import CapstoneDesign.Server.service.ReviewService;
import CapstoneDesign.Server.service.ZzimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreController {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final PaymentRepository paymentRepository;
    private final ReviewService reviewService;
    private final ZzimService zzimService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{storeId}")
    public ApiResponse getStoreDetail(@PathVariable("storeId") Long id, @TokenUser User user) {

        GuestUser guestUser = (GuestUser) user;

        Store findStore = storeRepository.findById(id).orElseThrow(() -> new NotFoundStoreException());
        OwnerUser ownerUser = findStore.getOwner();

        boolean zzimCheck = zzimService.checkZzimStore(guestUser, findStore); // 스토어 찜 여부
        List<StoreDetailMenuDTO> menuList = menuRepository.findMenuListByStoreId(findStore.getId()); // 메뉴 목록
        List<PaymentMethod> paymentMethods = paymentRepository.findPaymentMethodsByStoreId(findStore.getId()); // 결제수단 목록
        StoreDetailReviewDTO reviewSummary = reviewService.getStoreReviewSummary(findStore); // 리뷰 종합 정보

        StoreDetailDTO result = StoreDetailDTO.builder()
                .name(findStore.getName())
                .category(findStore.getCategory())
                .phoneNumber(ownerUser.getPhoneNumber())
                .location(findStore.getLocation())
                .closeTime(findStore.getCloseTime())
                .zzimCheck(zzimCheck)
                .menuList(menuList)
                .paymentMethods(paymentMethods)
                .reviewSummary(reviewSummary)
                .build();
        return new ApiResponse(HttpStatus.OK, "푸드트럭 상세페이지 조회 성공", result);
    }
}

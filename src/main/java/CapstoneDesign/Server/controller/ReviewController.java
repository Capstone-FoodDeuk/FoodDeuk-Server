package CapstoneDesign.Server.controller;

import CapstoneDesign.Server.config.annotation.TokenUser;
import CapstoneDesign.Server.domain.dto.ApiResponse;
import CapstoneDesign.Server.domain.dto.ReviewDTO;
import CapstoneDesign.Server.domain.entity.review.Review;
import CapstoneDesign.Server.domain.entity.store.Store;
import CapstoneDesign.Server.domain.entity.user.GuestUser;
import CapstoneDesign.Server.domain.entity.user.User;
import CapstoneDesign.Server.exception.NotFoundStoreException;
import CapstoneDesign.Server.repository.ReviewRepository;
import CapstoneDesign.Server.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guest/review")
public class ReviewController {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{storeId}")
    public ApiResponse createReview(@PathVariable("storeId") Long id, @RequestBody ReviewDTO review, @TokenUser User user) {

        GuestUser guestUser = (GuestUser) user;
        Store findStore = storeRepository.findById(id).orElseThrow(() -> new NotFoundStoreException());

        Review newReview = Review.createReview(findStore, guestUser);
        reviewRepository.save(newReview);

        return new ApiResponse(HttpStatus.CREATED, "리뷰 생성 성공", null);
    }
}

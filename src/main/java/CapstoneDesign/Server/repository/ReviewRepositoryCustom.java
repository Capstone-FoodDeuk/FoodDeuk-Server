package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.dto.ReviewContentDTO;
import CapstoneDesign.Server.domain.dto.UserPageReviewDTO;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<ReviewContentDTO> findReviewContentsByStoreId(Long id);
    List<UserPageReviewDTO> findMyReviewsByUserId(Long id);
}
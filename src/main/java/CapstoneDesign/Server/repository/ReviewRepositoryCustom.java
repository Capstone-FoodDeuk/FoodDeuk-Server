package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.dto.ReviewContentDTO;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<ReviewContentDTO> findReviewContentsByStoreId(Long id);
}
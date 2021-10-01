package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.entity.review.Review;
import CapstoneDesign.Server.domain.entity.review.ReviewContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
}
package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.dto.ReviewContentDTO;
import CapstoneDesign.Server.domain.dto.UserPageReviewDTO;
import CapstoneDesign.Server.domain.entity.review.QReview;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static CapstoneDesign.Server.domain.entity.review.QReview.review;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewContentDTO> findReviewContentsByStoreId(Long id) {

        return queryFactory
                .select(Projections.fields(ReviewContentDTO.class,
                        review.content.score,
                        review.content.taste,
                        review.content.quantity,
                        review.content.kindness))
                .from(review)
                .where(review.store.id.eq(id))
                .fetch();
    }

    @Override
    public List<UserPageReviewDTO> findMyReviewsByUserId(Long id) {

        return queryFactory
                .select(Projections.fields(UserPageReviewDTO.class,
                        review.store.name,
                        review.store.category,
                        review.content.score,
                        review.content.taste,
                        review.content.quantity,
                        review.content.kindness))
                .from(review)
                .where(review.author.id.eq(id))
                .fetch();
    }
}

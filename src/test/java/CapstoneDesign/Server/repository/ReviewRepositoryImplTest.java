package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.dto.ReviewContentDTO;
import CapstoneDesign.Server.domain.dto.ReviewDTO;
import CapstoneDesign.Server.domain.entity.review.Kindness;
import CapstoneDesign.Server.domain.entity.review.Quantity;
import CapstoneDesign.Server.domain.entity.review.Taste;
import CapstoneDesign.Server.domain.entity.store.Store;
import CapstoneDesign.Server.domain.entity.user.GuestUser;
import CapstoneDesign.Server.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewRepositoryImplTest {

    @Autowired
    EntityManager em;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewService reviewService;


    @Test
    public void 푸드트럭ID값으로_리뷰_내용_목록_가져오기() {

        // given
        Store store = new Store();
        GuestUser user1 = GuestUser.builder()
                .loginId("user1")
                .password("pwd1234")
                .nickname("나는유저")
                .phoneNumber("010-1234-5678")
                .build();
        GuestUser user2 = GuestUser.builder()
                .loginId("user2")
                .password("pwd1234")
                .nickname("나는유저")
                .phoneNumber("010-1234-5678")
                .build();
        em.persist(store);
        em.persist(user1);
        em.persist(user2);

        reviewService.createReview(store, user1,
                new ReviewDTO(3L, Taste.Good, Quantity.SoSo, Kindness.Bad));
        reviewService.createReview(store, user2,
                new ReviewDTO(4L, Taste.Good, Quantity.Enough, Kindness.Kind));

        // when
        List<ReviewContentDTO> result = reviewRepository.findReviewContentsByStoreId(store.getId());

        // then
        assertThat(result).hasSize(2);
        assertThat(result).extracting("score").containsExactly(3L, 4L);
        assertThat(result).extracting("taste").containsExactly(Taste.Good, Taste.Good);
        assertThat(result).extracting("quantity").containsExactly(Quantity.SoSo, Quantity.Enough);
        assertThat(result).extracting("kindness").containsExactly(Kindness.Bad, Kindness.Kind);
    }
}5
package CapstoneDesign.Server.domain.entity.review;

import CapstoneDesign.Server.domain.entity.BaseTimeEntity;
import CapstoneDesign.Server.domain.entity.store.Store;
import CapstoneDesign.Server.domain.entity.user.GuestUser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private GuestUser author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Embedded
    private ReviewContent content;

    /**
     * create Review
     */
    public static Review createReview(Store store, GuestUser guestUser) {
        Review review = new Review();
        review.store = store;
        store.getReviewList().add(review);
        guestUser.getReviewList().add(review);
        return review;
    }

    /**
     * set content
     */
    public void writeReview(Long score, Taste taste, Quantity quantity, Kindness kindness) {
        ReviewContent content = new ReviewContent(score, taste, quantity, kindness);
        this.content = content;
    }
}

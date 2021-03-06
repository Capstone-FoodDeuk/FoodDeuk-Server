package CapstoneDesign.Server.domain.entity.review;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewContent {

    private Long score;

    @Enumerated(EnumType.STRING)
    private Taste taste;

    @Enumerated(EnumType.STRING)
    private Quantity quantity;

    @Enumerated(EnumType.STRING)
    private Kindness kindness;
}
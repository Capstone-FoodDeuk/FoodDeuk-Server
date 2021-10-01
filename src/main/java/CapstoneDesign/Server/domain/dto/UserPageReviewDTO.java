package CapstoneDesign.Server.domain.dto;

import CapstoneDesign.Server.domain.entity.review.Kindness;
import CapstoneDesign.Server.domain.entity.review.Quantity;
import CapstoneDesign.Server.domain.entity.review.Taste;
import CapstoneDesign.Server.domain.entity.store.Category;
import lombok.Data;

@Data
public class UserPageReviewDTO {

    private String name;
    private Category category;
    private Long score;
    private Taste taste;
    private Quantity quantity;
    private Kindness kindness;
}

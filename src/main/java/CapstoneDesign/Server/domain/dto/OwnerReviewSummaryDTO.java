package CapstoneDesign.Server.domain.dto;

import CapstoneDesign.Server.domain.entity.review.Kindness;
import CapstoneDesign.Server.domain.entity.review.Quantity;
import CapstoneDesign.Server.domain.entity.review.Taste;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class OwnerReviewSummaryDTO {

    private String name;
    private Long userCount;
    private Long totalScore;
    private Map<Taste, Long> tasteScore;
    private Map<Quantity, Long> quantityScore;
    private Map<Kindness, Long> kindnessScore;
}

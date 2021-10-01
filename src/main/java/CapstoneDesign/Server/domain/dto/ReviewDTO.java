package CapstoneDesign.Server.domain.dto;

import CapstoneDesign.Server.domain.entity.review.Kindness;
import CapstoneDesign.Server.domain.entity.review.Quantity;
import CapstoneDesign.Server.domain.entity.review.Taste;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ReviewDTO {

    @NotBlank
    @Min(1)
    @Max(5)
    private Long score;

    @NotBlank
    private Taste taste;

    @NotBlank
    private Quantity quantity;

    @NotBlank
    private Kindness kindness;
}

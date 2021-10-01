package CapstoneDesign.Server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserPageDTO {

    private String nickname;
    private List<UserPageZzimDTO> zzims;
    private List<UserPageReviewDTO> myReviews;
}

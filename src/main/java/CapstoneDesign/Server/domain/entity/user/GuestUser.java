package CapstoneDesign.Server.domain.entity.user;

import CapstoneDesign.Server.domain.entity.Zzim;
import CapstoneDesign.Server.domain.entity.review.Review;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("GUEST")
@PrimaryKeyJoinColumn(name = "guest_id")
public class GuestUser extends User {

    @OneToMany(mappedBy = "author")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Zzim> zzimList = new ArrayList<>();

    @Builder
    public GuestUser(String loginId, String password, String nickname, String phoneNumber) {
        super(loginId, password, nickname, phoneNumber);
    }
}
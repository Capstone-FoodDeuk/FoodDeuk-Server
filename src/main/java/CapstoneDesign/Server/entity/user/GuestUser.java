package CapstoneDesign.Server.entity.user;

import CapstoneDesign.Server.entity.Zzim;
import CapstoneDesign.Server.entity.review.Review;
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

    @OneToMany(mappedBy = "user")
    private List<Zzim> zzimList = new ArrayList<>();
}
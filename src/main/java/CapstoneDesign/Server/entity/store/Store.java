package CapstoneDesign.Server.entity.store;

import CapstoneDesign.Server.entity.Zzim;
import CapstoneDesign.Server.entity.review.Review;
import CapstoneDesign.Server.entity.user.OwnerUser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "store")
    private OwnerUser owner;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    private String description;
    private String registerNum;

    @Embedded
    private Location location;
    private Boolean isActive;
    private LocalDateTime closeTime;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Menu> menuList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Zzim> zzimList = new ArrayList<>();

    /**
     * set owner
     */
    public void ownedBy(OwnerUser owner) {
        this.owner = owner;
    }
}

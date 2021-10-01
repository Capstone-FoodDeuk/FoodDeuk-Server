package CapstoneDesign.Server.domain.entity.store;

import CapstoneDesign.Server.domain.entity.review.Review;
import CapstoneDesign.Server.domain.entity.user.OwnerUser;
import CapstoneDesign.Server.domain.entity.Zzim;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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

    public void updateStoreInfo(String name, Category category, String registerNum, String description) {
        this.name = name;
        this.category = category;
        this.registerNum = registerNum;
        this.description = description;
    }

    public void updateStoreOpenInfo(Location location, LocalDateTime closeTime) {
        this.location = location;
        this.closeTime = closeTime;
    }

    public void storeOpen(Boolean isActive) {
        this.isActive = isActive;
    }
}

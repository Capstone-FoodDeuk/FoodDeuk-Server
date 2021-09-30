package CapstoneDesign.Server.entity;

import CapstoneDesign.Server.entity.store.Store;
import CapstoneDesign.Server.entity.user.GuestUser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Zzim {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zzim_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private GuestUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private Boolean isAlarmActive;
}

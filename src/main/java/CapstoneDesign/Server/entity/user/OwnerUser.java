package CapstoneDesign.Server.entity.user;

import CapstoneDesign.Server.entity.store.Store;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("OWNER")
@PrimaryKeyJoinColumn(name = "owner_id")
public class OwnerUser extends User{

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id")
    private Store store;
}
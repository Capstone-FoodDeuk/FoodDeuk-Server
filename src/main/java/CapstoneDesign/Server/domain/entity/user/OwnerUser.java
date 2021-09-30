package CapstoneDesign.Server.domain.entity.user;

import CapstoneDesign.Server.domain.entity.store.Store;
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

    public OwnerUser(String loginId, String password, String nickname, String phoneNumber, Store store) {
        super(loginId, password, nickname, phoneNumber);
        this.store = null;
    }

    /**
     * set Store
     */
    public void hasStore(Store store) {
        this.store = store;
        store.ownedBy(this);
    }
}
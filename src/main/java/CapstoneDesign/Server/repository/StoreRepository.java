package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.entity.store.Store;
import CapstoneDesign.Server.domain.entity.user.OwnerUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findStoreByOwner(OwnerUser owner);
}
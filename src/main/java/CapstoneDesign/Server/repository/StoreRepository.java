package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}

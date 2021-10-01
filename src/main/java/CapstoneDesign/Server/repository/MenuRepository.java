package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.entity.store.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long>, MenuRepositoryCustom {
}

package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.entity.Zzim;
import CapstoneDesign.Server.domain.entity.user.GuestUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZzimRepository extends JpaRepository<Zzim, Long>, ZzimRepostiroyCustom {

    List<Zzim> findZzimsByUser(GuestUser user);
}

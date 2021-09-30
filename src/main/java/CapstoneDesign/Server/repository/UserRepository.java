package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

    Optional<T> findUserByLoginId(String loginId);
}
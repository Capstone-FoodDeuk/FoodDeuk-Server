package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

}

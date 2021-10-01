package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.dto.UserPageZzimDTO;

import java.util.List;

public interface ZzimRepostiroyCustom {

    List<UserPageZzimDTO> findZzimsByUserId(Long id);
}

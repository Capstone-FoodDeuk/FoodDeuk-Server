package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.dto.StoreDetailMenuDTO;
import CapstoneDesign.Server.domain.entity.store.Store;

import java.util.List;

public interface MenuRepositoryCustom {

    List<StoreDetailMenuDTO> findMenuListByStoreId(Long id);
}

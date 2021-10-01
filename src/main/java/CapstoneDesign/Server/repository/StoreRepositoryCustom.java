package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.dto.HomeStoreDTO;
import CapstoneDesign.Server.domain.entity.store.Store;

import java.util.List;

public interface StoreRepositoryCustom {

    public List<HomeStoreDTO> findActiveStoresByLocationLessThanDistance(double latitude, double longitude, double distance);
}

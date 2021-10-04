package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.dto.HomeStoreDTO;

import java.util.List;

public interface StoreRepositoryCustom {

    List<HomeStoreDTO> findActiveStoresByLocationLessThanDistance(double latitude, double longitude, double distance);
}

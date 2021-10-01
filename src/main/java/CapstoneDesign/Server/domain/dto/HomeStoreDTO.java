package CapstoneDesign.Server.domain.dto;

import CapstoneDesign.Server.domain.entity.store.Category;
import lombok.Data;

@Data
public class HomeStoreDTO {

    private Long id;
    private Category category;
    private double latitude;
    private double longitude;
}

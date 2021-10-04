package CapstoneDesign.Server.domain.dto;

import CapstoneDesign.Server.domain.entity.store.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HomeStoreDTO {

    private Long id;
    private Category category;
    private double latitude;
    private double longitude;
}

package CapstoneDesign.Server.domain.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StoreDetailMenuDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Min(value = 10)
    private Long price;

    @NotNull
    private boolean soldOut;
}

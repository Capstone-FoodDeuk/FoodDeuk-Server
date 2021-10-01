package CapstoneDesign.Server.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class HomeRequestDTO {

    @NotBlank
    private Double longitude;

    @NotBlank
    private Double latitude;
}

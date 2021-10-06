package CapstoneDesign.Server.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MenuDeleteDTO {

    @NotBlank
    private Long id;
}

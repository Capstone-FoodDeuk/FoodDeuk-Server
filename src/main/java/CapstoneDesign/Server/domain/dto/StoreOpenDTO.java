package CapstoneDesign.Server.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class StoreOpenDTO {

    @NotNull
    public boolean isActive;
    @NotBlank
    private Double longitude;
    @NotBlank
    private Double latitude;
    @NotBlank
    public LocalDateTime closeTime;
}
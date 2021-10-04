package CapstoneDesign.Server.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
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

    @QueryProjection
    public StoreDetailMenuDTO(@NotBlank String name, @NotBlank @Min(value = 10) Long price, @NotNull boolean soldOut) {
        this.name = name;
        this.price = price;
        this.soldOut = soldOut;
    }
}

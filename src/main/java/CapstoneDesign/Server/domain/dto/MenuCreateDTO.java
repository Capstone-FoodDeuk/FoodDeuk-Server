package CapstoneDesign.Server.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class MenuCreateDTO {

    @NotBlank
    @Size(max = 10)
    private String name;

    @NotBlank
    @Min(10)
    private Long price;
    private MultipartFile image;
    private Boolean soldOut;
}

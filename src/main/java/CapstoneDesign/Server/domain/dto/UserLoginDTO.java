package CapstoneDesign.Server.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginDTO {

    @NotBlank
    private String loginId;

    private String password;
}

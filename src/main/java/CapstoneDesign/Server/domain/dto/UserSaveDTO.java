package CapstoneDesign.Server.domain.dto;

import CapstoneDesign.Server.domain.entity.user.UserRole;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserSaveDTO {

    private UserRole role;

    @NotBlank
    private String loginId;

    @NotBlank
    @Size(min = 6)
    private String pwd;

    @NotBlank
    private String pwdCheck;

    @NotBlank
    @Size(min = 2, max = 10)
    private String nickname;

    @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$")
    private String phoneNumber;
}
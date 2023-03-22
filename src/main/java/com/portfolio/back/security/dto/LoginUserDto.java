package com.portfolio.back.security.dto;
import javax.validation.constraints.NotBlank;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class LoginUserDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}

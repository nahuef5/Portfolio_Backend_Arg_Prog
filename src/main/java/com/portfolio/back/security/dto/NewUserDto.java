package com.portfolio.back.security.dto;
import java.util.*;
import javax.validation.constraints.*;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class NewUserDto {
    @NotBlank
    @Size(min=5, max=10)
    private String name;
    @NotBlank
    @Size(min=5, max=10)
    private String surname;
    @NotBlank
    @Size(min=5, max=10)
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min=8, max=20)
    private String password;
    private List<String>roles= new ArrayList<>();
}

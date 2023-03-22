package com.portfolio.back.email.model;
import javax.validation.constraints.*;
import lombok.*;
@AllArgsConstructor
@Getter
public class EmailModel {
    @NotNull @NotBlank
    private String from, body, name, subject;
    private final String to="*****************";
}
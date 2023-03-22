package com.portfolio.back.security.entity;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_user;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @Column
    @ElementCollection(fetch=FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<RoleEnum>roles;

    public Usuario(String name, String surname, String username, String email, String password, List<RoleEnum> roles) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
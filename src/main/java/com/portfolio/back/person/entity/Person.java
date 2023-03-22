package com.portfolio.back.person.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Person implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true)
    private Long id;
    @NotNull
    @Column(unique=true)
    private String name;
    @NotNull
    @Column(unique=true)
    private String surname;
    @NotNull
    @Column(unique=true)
    private String profession;
    @NotNull
    @Size(min = 5, max = 500)
    private String description;
    public Person(String name, String surname, String profession, String description) {
        this.name = name;
        this.surname = surname;
        this.profession = profession;
        this.description = description;
    }    
}

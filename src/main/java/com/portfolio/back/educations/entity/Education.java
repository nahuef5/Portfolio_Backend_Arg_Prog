package com.portfolio.back.educations.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Education implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_educ;
    @NotNull
    private String institution;
    @NotNull
    private String description;
    public Education(String institution, String description) {
        this.institution = institution;
        this.description = description;
    }
}

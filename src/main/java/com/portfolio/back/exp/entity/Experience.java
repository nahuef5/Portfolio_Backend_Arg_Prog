package com.portfolio.back.exp.entity;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Experience implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_exp;
    @NotNull
    private String company;
    @NotNull
    private String details;    
    @NotNull
    private String project;
    @NotNull
    private String projectDescription;

    public Experience(String company, String details, String project, String projectDescription) {
        this.company = company;
        this.project = project;
        this.details=details;
        this.projectDescription = projectDescription;
    }
    
}

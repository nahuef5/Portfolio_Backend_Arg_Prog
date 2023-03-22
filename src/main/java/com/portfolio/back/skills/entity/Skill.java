package com.portfolio.back.skills.entity;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
@Entity
@NoArgsConstructor
@Getter @Setter
public class Skill implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_skill;
    @NotNull
    private String skill;
    @NotNull
    private Float percentage;
    public Skill(String skill, Float percentage) {
        this.skill = skill;
        this.percentage = percentage;
    }
}

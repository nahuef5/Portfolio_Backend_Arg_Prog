package com.portfolio.back.skills.repo;

import com.portfolio.back.skills.entity.Skill;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer>{
    Optional<Skill>findBySkill(String skill);
    boolean existsBySkill(String skill);
}

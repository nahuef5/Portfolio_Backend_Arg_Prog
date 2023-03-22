package com.portfolio.back.educations.repo;

import com.portfolio.back.educations.entity.Education;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EduRepository extends JpaRepository<Education, Integer>{
    Optional<Education> findByInstitution(String institution);
    boolean existsByInstitution(String institution);
}

package com.portfolio.back.exp.repo;

import com.portfolio.back.exp.entity.Experience;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpRepository extends JpaRepository<Experience, Integer>{
    Optional<Experience>findByCompany(String company);
    boolean existsByCompany(String company);
}

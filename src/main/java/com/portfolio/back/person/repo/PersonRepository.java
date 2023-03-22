package com.portfolio.back.person.repo;

import com.portfolio.back.person.entity.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
    Optional<Person>findByName(String name);
}

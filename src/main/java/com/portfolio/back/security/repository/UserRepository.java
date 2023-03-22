package com.portfolio.back.security.repository;
import com.portfolio.back.security.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer>{
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<Usuario>findByUsernameOrEmail(String username, String email);
}

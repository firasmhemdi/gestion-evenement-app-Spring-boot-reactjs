package com.example.projetd.integration.repository;

import com.example.projetd.integration.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Méthode pour trouver un utilisateur par email
    Optional<User> findByEmail(String email);
}
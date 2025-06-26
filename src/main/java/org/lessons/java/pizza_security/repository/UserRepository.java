package org.lessons.java.pizza_security.repository;
import java.util.Optional;
import org.lessons.java.pizza_security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);    
}

package org.lessons.java.pizza_security.repository;
import org.lessons.java.pizza_security.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PizzaRepository extends JpaRepository <Pizza, Integer> {
    // Nuovo metodo per cercare pizze il cui nome contiene una certa stringa (case-insensitive)
    List<Pizza> findByNameContainingIgnoreCase(String name);
}

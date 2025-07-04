package org.lessons.java.pizza_security.repository;
import org.lessons.java.pizza_security.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
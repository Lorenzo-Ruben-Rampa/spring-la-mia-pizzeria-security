package org.lessons.java.pizza_security.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.lessons.java.pizza_security.repository.PizzaRepository;
import org.lessons.java.pizza_security.model.Ingredient;
import org.lessons.java.pizza_security.model.Pizza;
import java.util.List;
import java.util.Optional;
import org.lessons.java.pizza_security.repository.IngredientRepository;


@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientsRepository;

    public List<Ingredient> findAll() {
        return ingredientsRepository.findAll();
    }

    public Ingredient create (Ingredient ingredient) {
        return ingredientsRepository.save(ingredient);
    }

    public Ingredient update(Ingredient ingredient) {
        return ingredientsRepository.save(ingredient);
    }

    public Ingredient getById(Integer id) {
        Optional<Ingredient> ingredientAttempt = ingredientsRepository.findById(id);
        if (ingredientAttempt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient with ID " + id + " not found.");
        }
        return ingredientAttempt.get();
    }

    public void delete(Ingredient ingredient) {
        ingredientsRepository.delete(ingredient);
    }

    public void deleteById(Integer id) {
        Ingredient ingredient = getById(id);
        delete(ingredient);
    }

}
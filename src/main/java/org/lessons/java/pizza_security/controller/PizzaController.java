package org.lessons.java.pizza_security.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.lessons.java.pizza_security.repository.PizzaRepository;
import org.lessons.java.pizza_security.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import org.lessons.java.pizza_security.model.Pizza;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.lessons.java.pizza_security.model.SpecialOffer;
import org.lessons.java.pizza_security.repository.IngredientRepository;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    private final IngredientRepository ingredientsRepository;

    @Autowired
    private PizzaService pizzaService;

    PizzaController(IngredientRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    //INDEX
    @GetMapping
    public String index (Model model, Authentication authentication, @RequestParam(name="pizzaName", required=false) String searchPizzaName) {
        List<Pizza> pizzas;
        if (searchPizzaName != null && !searchPizzaName.isBlank()) {
            pizzas = pizzaService.findByName(searchPizzaName);
            model.addAttribute("searchPizzaName", searchPizzaName);
        } else {
            pizzas = pizzaService.findAll();
        }
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("username", authentication.getName());
        return "pizzas/index";
    }
    
    //SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", pizzaService.getById(id));
        return "/pizzas/pizzaDetail";
        }

    //CREATE
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredients", ingredientsRepository.findAll());
        model.addAttribute("pizza", new Pizza());  
        return "/pizzas/create";
    }

    // store del CREATE
    @PostMapping("/create")
    public String store (@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientsRepository.findAll());
            return "/pizzas/create";
        }
        pizzaService.create(formPizza);
        return "redirect:/pizzas";
    }

    //UPDATE
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", pizzaService.getById(id));
        model.addAttribute("ingredients", ingredientsRepository.findAll());
        return "/pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update (@PathVariable("id") Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientsRepository.findAll());
            return "/pizzas/edit";
        }
        pizzaService.update(formPizza);
        return "redirect:/pizzas";
    }

    //DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        pizzaService.deleteById(id);
        return "redirect:/pizzas";
    }
    
    //NUOVA ENTITY Special Offers
    @GetMapping("/{id}/special-offers")
    public String specialOffer(@PathVariable Integer id, Model model) {
        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.setPizza(pizzaService.getById(id));
        model.addAttribute("specialOffer", specialOffer);
        return "special-offers/create";
    }
}

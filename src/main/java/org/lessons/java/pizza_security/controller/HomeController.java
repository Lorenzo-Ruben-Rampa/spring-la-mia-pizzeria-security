package org.lessons.java.pizza_security.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
        @GetMapping
    public String home(Model model) {
        model.addAttribute("welcomeMessage", "Benvenuto a Pizza's Hut!");
        return "home";
    } 
    
}

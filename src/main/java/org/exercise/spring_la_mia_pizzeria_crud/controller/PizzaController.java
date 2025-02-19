package org.exercise.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.exercise.spring_la_mia_pizzeria_crud.model.Pizza;
import org.exercise.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(Model model) {
        List<Pizza> pizzas = repository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizza/index";
    }

    @GetMapping("/search")
    public String findByKeyword(@RequestParam(name = "name") String name, Model model) {
        List<Pizza> pizzas = repository.findByNameContainingIgnoreCase(name);
        model.addAttribute("pizzas", pizzas);
        return "pizza/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        // Pizza pizza = repository.findById(id).get();
        model.addAttribute("pizza", repository.findById(id).get());
        return "pizza/showPizza";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizza/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "pizza/create";
        }

        repository.save(pizzaForm);
        redirectAttributes.addFlashAttribute("message", "A new pizza has been added");
        return "redirect:/pizza";
    }

}

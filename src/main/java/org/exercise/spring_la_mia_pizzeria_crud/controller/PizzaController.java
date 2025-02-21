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
        model.addAttribute("create", true);
        model.addAttribute("pizza", new Pizza());
        return "pizza/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("create", true);
            return "pizza/create-or-edit";
        }

        repository.save(pizzaForm);
        redirectAttributes.addFlashAttribute("message", "A new pizza has been added");
        redirectAttributes.addFlashAttribute("alert", "alert-primary");
        return "redirect:/pizza";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());
        return "pizza/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "pizza/create-or-edit";
        }

        repository.save(pizzaForm);
        redirectAttributes.addFlashAttribute("message", "A pizza has been updated");
        redirectAttributes.addFlashAttribute("alert", "alert-success");
        return "redirect:/pizza";
    }

    @PostMapping("/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        
        Pizza pizza = repository.findById(id).get();

        repository.delete(pizza);
 
        redirectAttributes.addFlashAttribute("message", "A pizza has been updated");
        redirectAttributes.addFlashAttribute("alert", "alert-danger");
        return "redirect:/pizza";
    }
    
}

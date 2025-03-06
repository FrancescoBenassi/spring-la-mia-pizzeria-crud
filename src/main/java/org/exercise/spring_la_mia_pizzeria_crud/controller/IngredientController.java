package org.exercise.spring_la_mia_pizzeria_crud.controller;

import org.exercise.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.exercise.spring_la_mia_pizzeria_crud.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    
    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientService.findAll());
        return "ingredients/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredients", ingredientService.getbyId(id));
        return "ingredients/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient ingredientForm, BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes) {
       
                if (bindingResult.hasErrors()) {
                    model.addAttribute("create", true);
                    return "ingredients/create-or-edit";
                }
        
                ingredientService.create(ingredientForm);
                redirectAttributes.addFlashAttribute("message", "A new ingredient has been added");
                redirectAttributes.addFlashAttribute("alert", "alert-primary");
                return "redirect:/ingredients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientService.getbyId(id));
        return "ingredients/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient ingredientForm, BindingResult bindingResult, Model model,
    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-or-edit";
        }

        ingredientService.update(ingredientForm);
        redirectAttributes.addFlashAttribute("message", "A new ingredient has been updated");
        redirectAttributes.addFlashAttribute("alert", "alert-primary");
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Ingredient ingredient = ingredientService.getbyId(id);
        ingredientService.delete(ingredient);
        redirectAttributes.addFlashAttribute("message", "A ingredient has been deleted");
        redirectAttributes.addFlashAttribute("alert", "alert-danger");
        return "redirect:/pizza";
    }
}

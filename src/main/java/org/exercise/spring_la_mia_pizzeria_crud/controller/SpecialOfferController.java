package org.exercise.spring_la_mia_pizzeria_crud.controller;

import org.exercise.spring_la_mia_pizzeria_crud.model.Pizza;
import org.exercise.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.exercise.spring_la_mia_pizzeria_crud.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/specialOffers")
public class SpecialOfferController {

    @Autowired
    private SpecialOfferService specialOfferService;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer specialOfferForm,
            BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("create", true);
            return "specialOffers/create-or-edit";
        }

        specialOfferService.create(specialOfferForm);
        redirectAttributes.addFlashAttribute("message", "A new special offer has been created");
        redirectAttributes.addFlashAttribute("alert", "alert-primary");
        return "redirect:/pizza";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("specialOffer", specialOfferService.getById(id));
        return "specialOffers/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("specialOffer") SpecialOffer specialOfferForm,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "specialOffers/create-or-edit";
        }

        specialOfferService.update(specialOfferForm);
        redirectAttributes.addFlashAttribute("message", "A new special offer has been updated");
        redirectAttributes.addFlashAttribute("alert", "alert-success");
        return "redirect:/pizza";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        specialOfferService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "A special offer has been deleted");
        redirectAttributes.addFlashAttribute("alert", "alert-danger");
        return "redirect:/pizza";
    }

}

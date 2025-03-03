package org.exercise.spring_la_mia_pizzeria_crud.service;

import java.util.List;

import org.exercise.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.exercise.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public List<Ingredient> findBySort() {
        return ingredientRepository.findAll(Sort.by("name"));
    }

    public Ingredient getbyId(Integer id) {
        return ingredientRepository.findById(id).get();
    }

    public Boolean existById(Integer id) {
        return ingredientRepository.existsById(id);
    }

    public Ingredient create(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void delete(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }

    public void deleteById(Integer id) {
        ingredientRepository.deleteById(id);
    }

}

package org.exercise.spring_la_mia_pizzeria_crud.service;

import java.util.List;
import java.util.Optional;

import org.exercise.spring_la_mia_pizzeria_crud.model.Pizza;
import org.exercise.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.exercise.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.exercise.spring_la_mia_pizzeria_crud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    SpecialOfferRepository specialOfferRepository;

    public Pizza getById(Integer id) {
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if(pizza.isEmpty()){
            throw new IllegalArgumentException("Pizza not found with id " + id);
        }
        return pizza.get();
    }

    public Optional<Pizza> findById(Integer id){
        return pizzaRepository.findById(id);
    }

   public List<Pizza> findByName(String name) {
        return pizzaRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Pizza> findAll(){
        return pizzaRepository.findAll();
    }

    public Pizza create(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void delete( Pizza pizza){
        for (SpecialOffer specialOffer : pizza.getSpecialOffers()) {
            specialOfferRepository.delete(specialOffer);
        }
        pizzaRepository.delete(pizza);
    }

    public void deleteById(Integer id){
        pizzaRepository.deleteById(id);
    }
    
}

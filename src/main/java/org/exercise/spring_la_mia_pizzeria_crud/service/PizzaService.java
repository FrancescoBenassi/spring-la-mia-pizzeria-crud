package org.exercise.spring_la_mia_pizzeria_crud.service;

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

    public void deleteById( Pizza pizza){

        for (SpecialOffer specialOffer : pizza.getSpecialOffers()) {
            specialOfferRepository.delete(specialOffer);
        }
        pizzaRepository.delete(pizza);
    }
    
}

package org.exercise.spring_la_mia_pizzeria_crud.service;

import org.exercise.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.exercise.spring_la_mia_pizzeria_crud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {

    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    public SpecialOffer create(SpecialOffer specialOffer) {
        return specialOfferRepository.save(specialOffer);
    }

    public SpecialOffer update(SpecialOffer specialOffer) {
        return specialOfferRepository.save(specialOffer);
    }

    public SpecialOffer getById(Integer id) {
        return specialOfferRepository.findById(id).get();
    }

    public void deleteById(Integer id) {
        specialOfferRepository.deleteById(id);
    }
}

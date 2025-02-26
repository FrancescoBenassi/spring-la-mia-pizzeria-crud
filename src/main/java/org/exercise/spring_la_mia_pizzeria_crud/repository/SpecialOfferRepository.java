package org.exercise.spring_la_mia_pizzeria_crud.repository;

import org.exercise.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Integer>{
    
}

package org.exercise.spring_la_mia_pizzeria_crud.controller.api;

import java.util.List;
import java.util.Optional;

import org.exercise.spring_la_mia_pizzeria_crud.model.Pizza;
import org.exercise.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> index() {
        return pizzaService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Pizza> show(@PathVariable Integer id) {
        Optional<Pizza> pizzaTest = pizzaService.findById(id);

        if (pizzaTest.isPresent()) {
            return new ResponseEntity<Pizza>(pizzaTest.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Pizza> store(@Valid @RequestBody Pizza pizza) {
        return new ResponseEntity<Pizza>(pizzaService.create(pizza), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Pizza> update(@Valid @RequestBody Pizza pizza, @PathVariable Integer id) {
        Optional<Pizza> pizzaTest = pizzaService.findById(id);

        if (pizzaTest.isPresent()) {
            return new ResponseEntity<Pizza>(pizzaService.update(pizza), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Integer id){
        Optional<Pizza> pizzaTest = pizzaService.findById(id);

        if (pizzaTest.isPresent()) {
            pizzaService.deleteById(id);
            return new ResponseEntity<Pizza>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

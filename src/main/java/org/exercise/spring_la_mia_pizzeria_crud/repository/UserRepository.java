package org.exercise.spring_la_mia_pizzeria_crud.repository;

import java.util.Optional;

import org.exercise.spring_la_mia_pizzeria_crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);
    
}

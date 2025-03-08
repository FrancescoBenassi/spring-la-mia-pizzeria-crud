package org.exercise.spring_la_mia_pizzeria_crud.service;

import java.util.Optional;

import org.exercise.spring_la_mia_pizzeria_crud.model.User;
import org.exercise.spring_la_mia_pizzeria_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    
}

package org.exercise.spring_la_mia_pizzeria_crud.security;

import java.util.Optional;

import org.exercise.spring_la_mia_pizzeria_crud.model.User;
import org.exercise.spring_la_mia_pizzeria_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DatabaseUserDetailsService implements UserDetailsService{
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User> userAttempt = userService.findByUsername(username);
       if(userAttempt.isEmpty()){
        throw new UsernameNotFoundException("No user with username " + "have been found");
       }
       return new DatabaseUserDetails(userAttempt.get());
    }
    
}

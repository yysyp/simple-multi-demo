package com.jcompetence.opa.and.inmemory.authentication.controller;

import com.jcompetence.opa.and.inmemory.authentication.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@Slf4j
public class UserResource {

    @GetMapping
    public List<User> getAllUsers() {
        return List.of(new User("Patrick", "Field", 1),new User("Bobby", "Field", 1),
                new User("Milly", "Brown", 1), new User("John", "Cusack", 3));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody User user) {
        log.info("Added new User {}", user);
    }

    @PutMapping
    public User updateUserDetails(@RequestBody User user) {
        log.info("Update User details {}", user);
        return user;
    }
}

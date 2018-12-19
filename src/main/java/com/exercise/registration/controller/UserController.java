package com.exercise.registration.controller;

import com.exercise.registration.dto.User;
import com.exercise.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/api")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        return service.registerUser(user);
    }
}

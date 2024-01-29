package com.tweteroo.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dtos.UserDTO;
import com.tweteroo.api.models.UserModel;
import com.tweteroo.api.services.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody @Valid UserDTO body) {
        if (userService.existsByUsername(body.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        UserModel user = userService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}

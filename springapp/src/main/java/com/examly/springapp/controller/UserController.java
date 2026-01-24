package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/users")
    public void addUser() {
    }

    @GetMapping("/users")
    public void getUsers() {
    }

    @PutMapping("/users/{id}")
    public void updateUser() {
    }
}

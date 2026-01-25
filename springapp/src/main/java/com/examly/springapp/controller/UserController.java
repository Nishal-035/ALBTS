package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @PostMapping
    public void addUser() {
    }

    @GetMapping
    public void getAllUsers() {
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id) {
    }

    @RequestMapping("/{id}")
    public void getUserById(@PathVariable Long id) {
    }
}

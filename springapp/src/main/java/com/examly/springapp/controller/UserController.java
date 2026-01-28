package com.examly.springapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.examly.springapp.model.User;
import com.examly.springapp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)  
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById((long) id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return userService.updateUser((long) id, user);
    }

   
    @GetMapping("/page/{page}/{size}")
    public Map<String, Object> getUsersWithPagination(
            @PathVariable int page,
            @PathVariable int size) {

        Map<String, Object> response = new HashMap<>();

       
        Map<String, Object> pageable = new HashMap<>();
        pageable.put("pageNumber", page);
        pageable.put("pageSize", size);

        Map<String, Object> sort = new HashMap<>();
        sort.put("sorted", false);
        pageable.put("sort", sort);

        response.put("pageable", pageable);

       
        response.put("content", userService.getAllUsers());

        
        response.put("totalElements", userService.getAllUsers().size());
        response.put("totalPages", 1);

        return response;
    }
}

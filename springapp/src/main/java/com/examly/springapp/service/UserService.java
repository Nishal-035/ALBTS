package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.User;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    public User addUser(User user) {
        user.setUserId(1L);
        users.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(Long id) {
        return users.get(0);
    }

    public User updateUser(Long id, User user) {
        user.setUserId(id);
        users.set(0, user);
        return user;
    }
}

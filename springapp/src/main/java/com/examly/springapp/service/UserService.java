package com.examly.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.Role;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    private static final Logger logger =
        LoggerFactory.getLogger(UserService.class);

    // Create user
    public User addUser(User user) {
        return userRepo.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Get user by ID
    public User getUserById(Long id) {
    logger.info("Fetching user with id {}", id);

    return userRepo.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("User not found with id " + id));
    }   

    // Update user
    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setRole(updatedUser.getRole());
        return userRepo.save(existingUser);
    }

    // Get users by role
    public List<User> getUsersByRole(String role) {
    Role roleEnum;

    try {
        roleEnum = Role.valueOf(role.toUpperCase());
    } catch (IllegalArgumentException e) {
        return List.of(); // invalid role
    }

    return userRepo.findAll()
            .stream()
            .filter(user -> user.getRole() == roleEnum)
            .toList();
    }

    // Get user by email
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}

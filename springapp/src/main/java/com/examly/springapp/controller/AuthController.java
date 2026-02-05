package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.User;
import com.examly.springapp.security.JwtUtil;
import com.examly.springapp.service.UserService;
import com.examly.springapp.exception.BadRequestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger logger =
        LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
public Map<String, String> login(@RequestBody User loginUser) {

    User user = userService.getUserByEmail(loginUser.getEmail())
            .orElseThrow(() -> new BadRequestException("User not found"));
    logger.info("User found: {}", user.getEmail());
    logger.info("Stored password: {}", user.getPassword());
    logger.info("Input password: {}", loginUser.getPassword());
    if (user.getPassword() == null ||
        !user.getPassword().equals(loginUser.getPassword())) {
        throw new RuntimeException("Invalid credentials");
    }

    String token = jwtUtil.generateToken(
            user.getEmail(),
            user.getRole().name()
    );

    return Map.of("token", token);
}

    @PostMapping("/register")
    public User register(@RequestBody User newUser) {
        logger.info("Registering user: {} with password: {}", newUser.getEmail(), newUser.getPassword());
        User savedUser = userService.addUser(newUser);
        logger.info("User saved with password: {}", savedUser.getPassword());
        return savedUser;
    }

}

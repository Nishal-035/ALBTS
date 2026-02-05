package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.User;
import com.examly.springapp.security.JwtUtil;
import com.examly.springapp.service.UserService;

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

    logger.info("Login attempt for email: {}", loginUser.getEmail());

    User user = userService.getUserByEmail(loginUser.getEmail());

    if (user.getPassword() == null || 
    !user.getPassword().equals(loginUser.getPassword())) {
    throw new RuntimeException("Invalid credentials");
}

        String token = jwtUtil.generateToken(
            user.getUsername(),
            user.getRole().name()
        );

        logger.info("Login successful for user: {}", user.getUsername());

        return Map.of("token", token);
    }

}

package com.example.cleanitbackend.Controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cleanitbackend.Model.AuthResponse;
import com.example.cleanitbackend.Model.User;

@CrossOrigin(origins = {"http://localhost:4200", "http://frontend:4200"})
@RestController
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private List<User> users = new ArrayList<>();

    public AuthController() {
        // Mocking some initial users
        LOGGER.info("Creating initial users");
        users.add(new User("user1", "l.sottru@cleanit.com", "User One", "password", "Customer"));
        users.add(new User("user2", "employee@cleanit.com", "User Two", "password", "Employee"));
        users.add(new User("user3", "manager@cleanit.com", "User Three", "password", "Manager"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestParam String email, @RequestParam String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                LOGGER.info("User with email: " + email + " logged in.");
                return ResponseEntity.ok(new AuthResponse("Login successful. User role: " + user.getRole(), user.getRole()));
            }
        }
        LOGGER.info("Login failed for user with email: " + email);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Login failed. Invalid email or password.", null));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {
        // Check if the email is already registered
        for (User existingUser : users) {
            if (existingUser.getEmail().equals(user.getEmail())) {
                LOGGER.info("Registration failed. Email already exists.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new AuthResponse("Registration failed. Email already exists.", null));
            }
        }

        // Assuming registered users are customers by default
        user.setRole("Customer");
        users.add(user);
        LOGGER.info("User with email: " + user.getEmail() + " registered.");
        return ResponseEntity.ok(new AuthResponse("Registration successful.", user.getRole()));
    }  
}

package com.example.cleanitbackend.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cleanitbackend.Model.User;

@CrossOrigin(origins = {"http://localhost:4200", "http://frontend:4200"})
@RestController
public class AuthController {

    private List<User> users = new ArrayList<>();

    public AuthController() {
        // Mocking some initial users
        users.add(new User("user1", "l.sottru@cleanit.com", "User One", "password", "Customer"));
        users.add(new User("user2", "employee@cleanit.com", "User Two", "password", "Employee"));
        users.add(new User("user3", "manager@cleanit.com", "User Three", "password", "Manager"));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return ResponseEntity.ok("Login successful. User role: " + user.getRole());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed. Invalid email or password.");
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        // Check if the email is already registered
        for (User existingUser : users) {
            if (existingUser.getEmail().equals(user.getEmail())) {
                return "Registration failed. Email already exists.";
            }
        }

        // Assuming registered users are customers by default
        user.setRole("Customer");
        users.add(user);
        return "Registration successful.";
    }  
}

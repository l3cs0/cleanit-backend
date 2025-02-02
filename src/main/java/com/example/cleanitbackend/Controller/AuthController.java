package com.example.cleanitbackend.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cleanitbackend.Dto.LoginRequestDto;
import com.example.cleanitbackend.Dto.UserDto;
import com.example.cleanitbackend.Model.AuthResponse;
import com.example.cleanitbackend.Model.User;

@CrossOrigin(origins = {"http://localhost:4200", "http://frontend:4200"})
@RestController
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private List<User> users = new ArrayList<>();
    private AtomicLong userCounter = new AtomicLong();

    public AuthController() {
        LOGGER.info("Creating initial users");
        users.add(new User(userCounter.incrementAndGet(), "l.sottru@gmail.com", "Leon Sottru", "leon", "Customer"));
        users.add(new User(userCounter.incrementAndGet(), "customer2@cleanit.com", "Luisa Two", "password", "Customer"));
        users.add(new User(userCounter.incrementAndGet(), "customer3@cleanit.com", "Leopold Three", "password", "Customer"));
        users.add(new User(userCounter.incrementAndGet(), "customer4@cleanit.com", "Customer Four", "password", "Customer"));
        users.add(new User(userCounter.incrementAndGet(), "employee@cleanit.com", "Employee", "employee", "Employee"));
        users.add(new User(userCounter.incrementAndGet(), "manager@cleanit.com", "Manager", "manager", "Manager"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequestDto loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                LOGGER.info("User with email: " + email + " logged in.");
                return ResponseEntity.ok(new AuthResponse("Login successful. User role: " + user.getRole(), user.getRole(), user.getName(), user.getId()));
            }
        }
        LOGGER.info("Login failed for user with email: " + email);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Login failed. Invalid email or password.", null, null));
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthResponse> logout() {
        // Perform logout logic here
        LOGGER.info("User logged out.");
        return ResponseEntity.ok(new AuthResponse("Logout successful.", null, null));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserDto userDto) {
        // Check if the email is already registered
        for (User existingUser : users) {
            if (existingUser.getEmail().equals(userDto.getEmail())) {
                LOGGER.info("Registration failed. Email already exists.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new AuthResponse("Registration failed. Email already exists.", null, null));
            }
        }

        User user = new User(userCounter.incrementAndGet(), userDto.getEmail(), userDto.getName(), userDto.getPassword(), "Customer");
        users.add(user);
        LOGGER.info("User with email: " + user.getEmail() + " registered.");
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse("Registration successful.", user.getRole(), user.getName(), user.getId()));
    }  

    

    @GetMapping("/customers")
    public ResponseEntity<List<User>> getAllCustomers() {
        LOGGER.info("Retrieving all users");
        List<User> customerUsers = new ArrayList<>();
        for (User user : users) {
            if (user.getRole().equals("Customer")) {
                User customerUser = new User(user.getId(), user.getEmail(), user.getName(), null, user.getRole());
                customerUsers.add(customerUser);
            }
        }
        return ResponseEntity.ok(customerUsers);
    }

    public Optional<User> getUserById(Long userId) {
        return users.stream()
                .filter(user -> user.getId() == userId)
                .findFirst();
    }
}

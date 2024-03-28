package com.example.Remittance.controller;

import com.example.Remittance.dto.LoginRequest;
import com.example.Remittance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean isValid = userService.validateEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if (isValid) {
            // Successful authentication logic here.
            // You might return a JWT token or some form of session identifier.
            return ResponseEntity.ok().body("Login successful.");
        } else {
            // Respond with an appropriate message and HTTP status.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        }
    }
}

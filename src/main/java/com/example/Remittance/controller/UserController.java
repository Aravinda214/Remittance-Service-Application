package com.example.Remittance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Remittance.entity.User;
import com.example.Remittance.enums.UserRole;
import com.example.Remittance.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    
    @GetMapping("/makers")
    public ResponseEntity<List<User>> getUsersWithMakerRole() {
        List<User> users = userService.getUsersByRole(UserRole.MAKER);
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/checkers")
    public ResponseEntity<List<User>> getUsersWithCheckerRole() {
        List<User> users = userService.getUsersByRole(UserRole.CHECKER);
        return ResponseEntity.ok(users);
    }
}

package com.example.Remittance.service;

import java.util.List;

import com.example.Remittance.entity.User;
import com.example.Remittance.enums.UserRole;

public interface UserService {
	User createUser(User user);
	boolean validateEmailAndPassword(String email, String password);
	List<User> getUsersByRole(UserRole role);
}
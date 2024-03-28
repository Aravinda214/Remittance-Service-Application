package com.example.Remittance.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Remittance.dao.UserRepository;
import com.example.Remittance.entity.User;
import com.example.Remittance.enums.UserRole;
import com.example.Remittance.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public User createUser(User user) {
        validateUserUniqueness(user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public boolean validateEmailAndPassword(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return bCryptPasswordEncoder.matches(password, user.getPassword());
        }

        return false;
    }
    
    @Override
    public List<User> getUsersByRole(UserRole role) {
        return userRepository.findByRole(role);
    }

    private void validateUserUniqueness(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use.");
        }
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new IllegalArgumentException("Username already in use.");
        }
        if (userRepository.existsByBankAccountNumber(user.getBankAccountNumber())) {
            throw new IllegalArgumentException("Bank account number already in use.");
        }
        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number already in use.");
        }
        if (user.getRole() == null) {
            throw new IllegalArgumentException("User role is required.");
        }
    }
}


package com.example.Remittance.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Remittance.entity.User;
import com.example.Remittance.enums.UserRole;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
    boolean existsByBankAccountNumber(String bankAccountNumber);
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<User> findByEmail(String email);
    List<User> findByRole(UserRole role);
}

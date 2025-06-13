package com.example.validate_form_signup.repository;

import com.example.validate_form_signup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}

package com.example.user.product_ready_features.product_ready_features.repositories;

import com.example.user.product_ready_features.product_ready_features.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}

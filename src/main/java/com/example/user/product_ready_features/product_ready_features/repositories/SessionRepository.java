package com.example.user.product_ready_features.product_ready_features.repositories;

import com.example.user.product_ready_features.product_ready_features.entities.Session;
import com.example.user.product_ready_features.product_ready_features.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session,Long> {
    List<Session> findByUser(User user);

    Optional<Session> findByRefreshToken(String refreshToken);
}

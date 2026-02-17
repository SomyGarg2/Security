package com.example.user.product_ready_features.product_ready_features.services;

import com.example.user.product_ready_features.product_ready_features.exceptions.ResourceNotFoundException;
import com.example.user.product_ready_features.product_ready_features.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).
                orElseThrow(() -> new ResourceNotFoundException("User not found with email: "+username));
    }
}

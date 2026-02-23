package com.example.user.product_ready_features.product_ready_features.services;

import com.example.user.product_ready_features.product_ready_features.dtos.LoginDto;
import com.example.user.product_ready_features.product_ready_features.dtos.SignupDto;
import com.example.user.product_ready_features.product_ready_features.dtos.UserDto;
import com.example.user.product_ready_features.product_ready_features.entities.User;
import com.example.user.product_ready_features.product_ready_features.exceptions.ResourceNotFoundException;
import com.example.user.product_ready_features.product_ready_features.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).
                orElseThrow(() -> new BadCredentialsException("User not found with email: "+username));
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id: "+userId));
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElse(null);
    }


    public UserDto signup(SignupDto signupDto) {
     Optional<User> user =  userRepository.findByEmail(signupDto.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User already exists with email: "+signupDto.getEmail());
        }
        User toBeCreated = modelMapper.map(signupDto, User.class);
        toBeCreated.setPassword(passwordEncoder.encode(toBeCreated.getPassword()));

        User savedUser = userRepository.save(toBeCreated);

        return modelMapper.map(savedUser, UserDto.class);

    }


    public User save(User newUser) {
        return userRepository.save(newUser);
    }
}

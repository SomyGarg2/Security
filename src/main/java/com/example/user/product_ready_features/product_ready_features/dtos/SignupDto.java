package com.example.user.product_ready_features.product_ready_features.dtos;

import com.example.user.product_ready_features.product_ready_features.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignupDto {
    private  String email;
    private String password;
    private String name;

    private Set<Role> roles;  // we don't do this in real life, but for the sake of simplicity, we will allow the user to choose their role during signup
}

package com.example.user.product_ready_features.product_ready_features.controllers;

import com.example.user.product_ready_features.product_ready_features.dtos.LoginDto;
import com.example.user.product_ready_features.product_ready_features.dtos.LoginResponseDto;
import com.example.user.product_ready_features.product_ready_features.dtos.SignupDto;
import com.example.user.product_ready_features.product_ready_features.dtos.UserDto;
import com.example.user.product_ready_features.product_ready_features.services.AuthService;
import com.example.user.product_ready_features.product_ready_features.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupDto signupDto){
        UserDto userDto = userService.signup(signupDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response){
        LoginResponseDto loginResponseDto = authService.login(loginDto);

        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refreshToken(HttpServletRequest request){
       String refreshToken =  Arrays.stream(request.getCookies()).
                filter(cookie -> "refreshToken".equals(cookie.getName())).
                findFirst().
                map(Cookie::getValue).
                orElseThrow(() -> new RuntimeException("Refresh token not found inside the cookies"));

        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(loginResponseDto);
    }
}

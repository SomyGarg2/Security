package com.example.user.product_ready_features.product_ready_features;

import com.example.user.product_ready_features.product_ready_features.entities.User;
import com.example.user.product_ready_features.product_ready_features.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductReadyFeaturesApplicationTests {

    @Autowired
    private JwtService jwtService;


	@Test
	void contextLoads() {
////        User user = new User(4L, "anuj@gmail.com", "1234");
//
//        String token = jwtService.generateToken(user);
//
//        System.out.println(token);
//
//        Long id = jwtService.getUserIdFromToken(token);
//        System.out.println(id);
	}



}

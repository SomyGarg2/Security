package com.example.user.product_ready_features.product_ready_features.controllers;

import com.example.user.product_ready_features.product_ready_features.dtos.ProductDto;
import com.example.user.product_ready_features.product_ready_features.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto input){
        return productService.createNewProduct(input);
    }

}
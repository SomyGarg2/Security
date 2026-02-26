package com.example.user.product_ready_features.product_ready_features.services;

import com.example.user.product_ready_features.product_ready_features.dtos.ProductDto;
import com.example.user.product_ready_features.product_ready_features.entities.ProductEntity;
import com.example.user.product_ready_features.product_ready_features.entities.User;
import com.example.user.product_ready_features.product_ready_features.exceptions.ResourceNotFoundException;
import com.example.user.product_ready_features.product_ready_features.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDto> getAllProducts(){
        return productRepository.findAll()
                .stream().map(productEntity -> modelMapper.map(productEntity,ProductDto.class))
                .collect(Collectors.toList());
    }

    public ProductDto createNewProduct(ProductDto input){
        ProductEntity productEntity = modelMapper.map(input,ProductEntity.class);

        ProductEntity savedProductEntity = productRepository.save(productEntity);

        return modelMapper.map(savedProductEntity,ProductDto.class);
    }

    public ProductDto getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found with id: "+id));

        return modelMapper.map(productEntity,ProductDto.class);
    }
}
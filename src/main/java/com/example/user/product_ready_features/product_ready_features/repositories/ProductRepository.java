package com.example.user.product_ready_features.product_ready_features.repositories;

import com.example.user.product_ready_features.product_ready_features.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

}
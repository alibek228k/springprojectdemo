package com.example.springprojectdemo.repository;

import com.example.springprojectdemo.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByTokenSession(String sessionToken);
}

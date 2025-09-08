package com.example.di.app.springboot_di.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.di.app.springboot_di.models.Product;

@Repository
public class ProductRepositorySecondaryImpl implements IProductRepository {

    @Override
    public List<Product> findAll() {
        return List.of(
            new Product(6L, "Cámara", 400.0),
            new Product(7L, "Auriculares", 80.0),
            new Product(8L, "Altavoz", 120.0)
        );
    }

    @Override
    public Product findById(Long id) {
        return findAll().stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);
    }
}

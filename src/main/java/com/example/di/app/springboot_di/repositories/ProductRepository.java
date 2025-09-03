package com.example.di.app.springboot_di.repositories;

import java.util.ArrayList;
import java.util.List;

import com.example.di.app.springboot_di.models.Product;

public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>(
            List.of(
                new Product(1L, "Monitor", 200.0),
                new Product(2L, "Teléfono", 100.0),
                new Product(3L, "Tablet", 300.0),
                new Product(4L, "Laptop", 800.0)
            )
        );
    }

    public List<Product> findAll() {
        return products;
    }
}

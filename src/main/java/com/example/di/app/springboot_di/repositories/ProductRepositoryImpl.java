package com.example.di.app.springboot_di.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.di.app.springboot_di.models.Product;

@Component
public class ProductRepositoryImpl implements IProductRepository {

    private List<Product> products;

    public ProductRepositoryImpl() {
        this.products = new ArrayList<>(
                List.of(
                        new Product(1L, "Monitor", 200.0),
                        new Product(2L, "Teléfono", 100.0),
                        new Product(3L, "Tablet", 300.0),
                        new Product(4L, "Laptop", 800.0),
                        new Product(5L, "Smartwatch", 150.0)));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(Long id) {
        return products.stream() // convierte la lista en un stream
                .filter(p -> p.getId().equals(id)) // filtra por id
                .findFirst() // toma el primero que coincida
                .orElse(null); // si no hay ninguno, retorna null
    }
}

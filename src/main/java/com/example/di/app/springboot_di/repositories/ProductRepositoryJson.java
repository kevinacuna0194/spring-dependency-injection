package com.example.di.app.springboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.example.di.app.springboot_di.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductRepositoryJson implements IProductRepository {

    private List<Product> products;

    public ProductRepositoryJson() {
        Resource resource = new ClassPathResource("json/products.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            products = Arrays.asList(objectMapper.readValue(resource.getFile(), Product[].class));
        } catch (Exception e) {
            throw new RuntimeException("Error reading products from JSON", e);
        }
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}

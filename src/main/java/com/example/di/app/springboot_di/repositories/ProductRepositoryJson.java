package com.example.di.app.springboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.example.di.app.springboot_di.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductRepositoryJson implements IProductRepository {

    private List<Product> products;

    // Forma imperativa de inyectar el recurso json dentro de la clase usando ClassPathResource
    public ProductRepositoryJson() {
        Resource resource = new ClassPathResource("json/product.json");
        readValueJson(resource);
    }

    // Forma declarativa de inyectar el recurso json desde el constructor de la clase AppConfig usando @Value
    public ProductRepositoryJson(Resource resource) {
        readValueJson(resource);
    }

    private void readValueJson(Resource resource) {
        // Resource resource = new ClassPathResource("json/product.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            products = Arrays.asList(objectMapper.readValue(resource.getInputStream(), Product[].class));
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

package com.example.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.di.app.springboot_di.models.Product;
import com.example.di.app.springboot_di.repositories.IProductRepository;

/** Logica de Negocio */
@Component
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        // stream es una secuencia de datos 
        // map aplica una transformación a cada producto
        // Aplicamos un 22% de IVA a cada producto
        // collect convierte el stream de vuelta a una lista
        return productRepository.findAll().stream().map(p -> {
            Double priceWithTax = p.getPrice() * 1.22; // IVA 22%
            // Product product = new Product(p.getId(), p.getName(), priceWithTax);
            Product product = (Product) p.clone();
            product.setPrice(priceWithTax);
            return product;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }
}

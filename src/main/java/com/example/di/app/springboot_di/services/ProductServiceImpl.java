package com.example.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import com.example.di.app.springboot_di.models.Product;
import com.example.di.app.springboot_di.repositories.ProductRepositoryImpl;

/** Capa Acceso a Datos */
public class ProductServiceImpl implements IProductService {

    private ProductRepositoryImpl productRepository = new ProductRepositoryImpl();

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

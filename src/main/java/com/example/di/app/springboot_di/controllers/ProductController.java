package com.example.di.app.springboot_di.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.di.app.springboot_di.models.Product;
import com.example.di.app.springboot_di.services.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService = new ProductService();

    @GetMapping("/list") // http://localhost:8080/api/product/list
    public List<Product> list() {
        return productService.findAll();
    }

    @GetMapping("/detail/{id}") // http://localhost:8080/api/product/detail/2
    public Product detail(@PathVariable Long id) {
        return productService.findById(id);
    }
}

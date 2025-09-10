package com.example.di.app.springboot_di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import com.example.di.app.springboot_di.repositories.IProductRepository;
import com.example.di.app.springboot_di.repositories.ProductRepositoryJson;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Value("classpath:json/product.json")
    private Resource resource;

    @Bean("beanProductRepositoryJson") // Define un Bean en el contexto de Spring
    // @Primary // Marca este Bean como el principal cuando hay múltiples implementaciones
    public IProductRepository productRepositoryJson() {
        return new ProductRepositoryJson(resource);
    }
}

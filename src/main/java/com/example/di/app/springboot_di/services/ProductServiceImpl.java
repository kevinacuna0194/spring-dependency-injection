package com.example.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.di.app.springboot_di.models.Product;
import com.example.di.app.springboot_di.repositories.IProductRepository;

/** Logica de Negocio */
@Service
public class ProductServiceImpl implements IProductService {

    // @Autowired
    // @Qualifier("beanProductRepositorySecondary") // Especifica el Bean a inyectar por nombre
    private IProductRepository productRepository;

    // @Autowired
    // private Environment environment;

    @Value("${config.priceTax:1.21}") // Valor por defecto 1.21 Si no existe la propiedad.
    private Double priceTax;

    // Inyeccion de Dependencias via Constructor
    // @Autowired // Opcional en Spring si solo hay un constructor
    public ProductServiceImpl(@Qualifier("beanProductRepositoryJson") IProductRepository productRepository ) {
        this.productRepository = productRepository;
    }

    // Inyeccion de Dependencias via Setter
    // @Autowired
    // public void setProductRepository(IProductRepository productRepository) {
    // this.productRepository = productRepository;
    // }

    @Override
    public List<Product> findAll() {
        // stream es una secuencia de datos
        // map aplica una transformación a cada producto
        // Aplicamos un 22% de IVA a cada producto
        // collect convierte el stream de vuelta a una lista
        return productRepository.findAll().stream().map(p -> {

            // System.out.println(environment.getProperty("config.priceTax"));
            // System.out.println(priceTax);

            Double priceWithTax = p.getPrice() * priceTax; // IVA 22%

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

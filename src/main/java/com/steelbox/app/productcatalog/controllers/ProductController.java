package com.steelbox.app.productcatalog.controllers;

import com.steelbox.app.productcatalog.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return null;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable String id) {
        return new Product();
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        Product product1 = new Product();
        product1.setId(product.getId());
        product1.setName(product.getName());
        return product1;
    }
}

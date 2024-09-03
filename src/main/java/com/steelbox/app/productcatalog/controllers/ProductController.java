package com.steelbox.app.productcatalog.controllers;

import com.steelbox.app.productcatalog.dtos.ProductDto;
import com.steelbox.app.productcatalog.models.Product;
import com.steelbox.app.productcatalog.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return null;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ProductId is invalid");
        }
        Product product = productService.getProductById(id);
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setCategoryName(product.getCategory().getName());
        productDto.setPrice(product.getPrice());
        return ResponseEntity.ok(productDto);
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return null;
    }
}

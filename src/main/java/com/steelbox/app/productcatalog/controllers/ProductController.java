package com.steelbox.app.productcatalog.controllers;

import com.steelbox.app.productcatalog.dtos.ProductDto;
import com.steelbox.app.productcatalog.exceptions.NotFoundException;
import com.steelbox.app.productcatalog.models.Category;
import com.steelbox.app.productcatalog.models.Product;
import com.steelbox.app.productcatalog.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            productDtoList.add(getProductDto(product));
        }
        return productDtoList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ProductId is invalid");
        }
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(getProductDto(product));
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        if (productDto.getCategoryName() == null) {
            throw new IllegalArgumentException("CategoryName is invalid");
        }
        Product product = productService.createProduct(getProduct(productDto));
        return getProductDto(product);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        if (id <= 0) {
            throw new IllegalArgumentException("ProductId is invalid");
        }
        Product product = productService.updateProduct(id, getProduct(productDto));
        return getProductDto(product);
    }

    @DeleteMapping("/{id}")
    public ProductDto deleteProduct(@PathVariable Long id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ProductId is invalid");
        }
        Product product = productService.deleteProduct(id);
        if (product == null) {
            throw new NotFoundException("productId: " + id + " was not found");
        }
        return getProductDto(product);
    }

    private ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setCategoryName(product.getCategory().getName());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        if (productDto.getCategoryName() != null) {
            Category category = new Category();
            category.setName(productDto.getCategoryName());
            product.setCategory(category);
        }
        product.setPrice(productDto.getPrice());
        return product;
    }
}

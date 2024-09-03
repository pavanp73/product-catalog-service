package com.steelbox.app.productcatalog.services;

import com.steelbox.app.productcatalog.models.Product;

import java.util.List;

public interface IProductService {

    Product getProductById(Long id) throws Exception;
    Product createProduct(Product product);
    List<Product> getAllProducts();
}

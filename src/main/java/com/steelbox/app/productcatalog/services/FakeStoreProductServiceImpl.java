package com.steelbox.app.productcatalog.services;

import com.steelbox.app.productcatalog.dtos.FakeStoreProductDto;
import com.steelbox.app.productcatalog.exceptions.NotFoundException;
import com.steelbox.app.productcatalog.models.Category;
import com.steelbox.app.productcatalog.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements IProductService {

    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Product getProductById(Long id) throws Exception {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity 
                = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, id);
        if (fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful() 
                && fakeStoreProductDtoResponseEntity.getBody() != null) {
            return getProduct(fakeStoreProductDtoResponseEntity.getBody());
        }
        if (fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful()
                && !fakeStoreProductDtoResponseEntity.hasBody()) {
            throw new NotFoundException("Product not found");
        }
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    private Product getProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        if (fakeStoreProductDto.getCategory() != null) {
            Category category = new Category();
            category.setName(fakeStoreProductDto.getCategory());
            product.setCategory(category);
        }
        return product;
    }
}

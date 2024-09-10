package com.steelbox.app.productcatalog.services;

import com.steelbox.app.productcatalog.client.FakeStoreApiClient;
import com.steelbox.app.productcatalog.dtos.FakeStoreProductDto;
import com.steelbox.app.productcatalog.exceptions.NotFoundException;
import com.steelbox.app.productcatalog.models.Category;
import com.steelbox.app.productcatalog.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements IProductService {

    private final FakeStoreApiClient fakeStoreApiClient;

    @Autowired
    public FakeStoreProductServiceImpl(FakeStoreApiClient fakeStoreApiClient) {
        this.fakeStoreApiClient = fakeStoreApiClient;
    }

    @Override
    public Product getProductById(Long id) throws Exception {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                fakeStoreApiClient.getProductById(id);

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
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                fakeStoreApiClient.createProduct(getFakeStoreProductDto(product));

        if (fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful()
                && fakeStoreProductDtoResponseEntity.getBody() != null) {
            return getProduct(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                fakeStoreApiClient.updateProduct(id, getFakeStoreProductDto(product));

        if (fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful()
                && fakeStoreProductDtoResponseEntity.getBody() != null) {
            return getProduct(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                fakeStoreApiClient.deleteProduct(id);

        if (fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful()
                && fakeStoreProductDtoResponseEntity.getBody() != null) {
            return getProduct(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;
    }


    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoListResponseEntity = fakeStoreApiClient.getAllProducts();

        if (fakeStoreProductDtoListResponseEntity.getStatusCode().is2xxSuccessful()
                && fakeStoreProductDtoListResponseEntity.getBody() != null) {
            List<Product> products = new ArrayList<>();
            for (FakeStoreProductDto dto : fakeStoreProductDtoListResponseEntity.getBody()) {
                products.add(getProduct(dto));
            }
            return products;
        }
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

    private FakeStoreProductDto getFakeStoreProductDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        if (product.getId() != null) {
            fakeStoreProductDto.setId(product.getId());
        }
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        return fakeStoreProductDto;
    }
}

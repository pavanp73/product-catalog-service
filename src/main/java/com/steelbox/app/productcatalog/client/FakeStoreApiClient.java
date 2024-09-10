package com.steelbox.app.productcatalog.client;

import com.steelbox.app.productcatalog.dtos.FakeStoreProductDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FakeStoreApiClient implements GenericClient {

    private static final String API_BASE_URL = "https://fakestoreapi.com/products";

    public ResponseEntity<FakeStoreProductDto> createProduct(FakeStoreProductDto fakeStoreProductDto) {
        return requestForEntity(
                API_BASE_URL,
                fakeStoreProductDto,
                HttpMethod.POST,
                FakeStoreProductDto.class
        );
    }

    public ResponseEntity<FakeStoreProductDto> updateProduct(Long id, FakeStoreProductDto fakeStoreProductDto) {
        return requestForEntity(
                API_BASE_URL + "/{id}",
                fakeStoreProductDto,
                HttpMethod.PUT,
                FakeStoreProductDto.class,
                id
        );
    }

    public ResponseEntity<FakeStoreProductDto> getProductById(Long id) {
        return requestForEntity(
                API_BASE_URL + "/{id}",
                null,
                HttpMethod.GET,
                FakeStoreProductDto.class,
                id
        );
    }

    public ResponseEntity<FakeStoreProductDto[]> getAllProducts() {
        return requestForEntity(
                API_BASE_URL,
                null,
                HttpMethod.GET,
                FakeStoreProductDto[].class
        );
    }

    public ResponseEntity<FakeStoreProductDto> deleteProduct(Long id) {
        return requestForEntity(
                API_BASE_URL + "/{id}",
                null,
                HttpMethod.DELETE,
                FakeStoreProductDto.class,
                id
        );
    }
}

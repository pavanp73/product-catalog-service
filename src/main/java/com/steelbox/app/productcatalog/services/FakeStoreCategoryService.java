package com.steelbox.app.productcatalog.services;

import com.steelbox.app.productcatalog.client.FakeStoreApiClient;
import com.steelbox.app.productcatalog.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreCategoryService implements ICategoryService {

    private final FakeStoreApiClient fakeStoreApiClient;

    @Autowired
    public FakeStoreCategoryService(FakeStoreApiClient fakeStoreApiClient) {
        this.fakeStoreApiClient = fakeStoreApiClient;
    }

    @Override
    public List<Category> getAllCategories() {
        ResponseEntity<String[]> fakeStoreApiClientAllCategories = fakeStoreApiClient.getAllCategories();
        if (fakeStoreApiClientAllCategories.getStatusCode().is2xxSuccessful()
                && fakeStoreApiClientAllCategories.getBody() != null) {
            return Arrays.stream(fakeStoreApiClientAllCategories.getBody()).toList()
                    .stream().map(s -> {
                        Category category = new Category();
                        category.setName(s);
                        return category;
                    }).toList();
        }
        return List.of();
    }
}

package com.steelbox.app.productcatalog.services;

import com.steelbox.app.productcatalog.models.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getAllCategories();
}

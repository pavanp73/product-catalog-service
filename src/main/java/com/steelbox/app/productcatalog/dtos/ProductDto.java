package com.steelbox.app.productcatalog.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String categoryName;
    private Double price;
}

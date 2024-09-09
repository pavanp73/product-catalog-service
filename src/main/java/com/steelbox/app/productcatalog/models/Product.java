package com.steelbox.app.productcatalog.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product extends BaseModel {

    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private Category category;
}

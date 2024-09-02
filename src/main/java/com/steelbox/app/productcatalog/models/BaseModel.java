package com.steelbox.app.productcatalog.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public abstract class BaseModel {

    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private Status status;
}

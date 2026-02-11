package com.minthitoo.ecommerce_backend.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AdminProductSkuDto {

    private Long id;
    private String sku;
    private Integer purchasePrice;
    private Integer sellingPrice;
    private String variantImage;
    private Integer stock;
    private Boolean status;
    private Integer weight;
    private Integer length;
    private Integer breadth;
    private Integer height;
    private Date createdAt;
    private Date updatedAt;
}

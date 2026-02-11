package com.minthitoo.ecommerce_backend.models.dtos;

import com.minthitoo.ecommerce_backend.utils.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AdminProductQueryDto {

    private Long id;
    private String name;
    private String slug;
    private ProductType productType;
    private String thumbnailImage;
    private String description;
    private String modelNumber;
    private Integer minimumOrderQuantity;
    private Integer maximumOrderQuantity;
    private Boolean isPhysical;
    private Boolean isActive;
    private Integer stock;
    private Date createdAt;
    private Date updatedAt;

}

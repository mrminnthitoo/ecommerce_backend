package com.minthitoo.ecommerce_backend.models.dtos;

import com.minthitoo.ecommerce_backend.utils.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AdminProductDto {

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
    private AdminCategoryDto category;
    private List<AdminProductSkuDto> skus;
    private List<AdminProductGalleryImageDto> galleryImages;
    private Date createdAt;
    private Date updatedAt;

}

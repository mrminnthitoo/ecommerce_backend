package com.minthitoo.ecommerce_backend.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AdminProductGalleryImageDto {

    private Long id;
    private String image;
    private Date createdAt;
    private Date updatedAt;

}

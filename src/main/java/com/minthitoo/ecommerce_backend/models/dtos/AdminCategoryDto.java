package com.minthitoo.ecommerce_backend.models.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminCategoryDto {

    private Long id;
    private String name;
    private String slug;
    private String image;

}

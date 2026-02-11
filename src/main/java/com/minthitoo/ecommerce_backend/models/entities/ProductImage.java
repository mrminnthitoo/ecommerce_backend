package com.minthitoo.ecommerce_backend.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_images")
public class ProductImage extends Base{

    @Column(unique = true)
    private String name;

    @Column
    private String originalName;


}

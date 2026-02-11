package com.minthitoo.ecommerce_backend.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_gallery_images")
public class ProductGalleryImage extends Base{

    @Column
    private String image;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "product_id",
            nullable = false
    )
    private Product product;

}

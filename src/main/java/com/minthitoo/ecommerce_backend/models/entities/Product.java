package com.minthitoo.ecommerce_backend.models.entities;

import com.minthitoo.ecommerce_backend.utils.enums.ProductType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends Base{

    @Column(length = 200)
    private String name;

    @Column(
            length = 20,
            unique = true
    )
    private String slug;

    @Column
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(length = 200)
    private String thumbnailImage;

    @Column(length = 5000)
    private String description;

    @Column(
            length = 20,
            unique = true
    )
    private String modelNumber;

    @Column
    private Integer minimumOrderQuantity = 1;

    @Column
    private Integer maximumOrderQuantity = 1;

    @Column
    private Boolean isPhysical;

    @Column
    private Boolean isActive = true;

    @Column
    private Integer stock;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(
            name = "category_id"
    )
    private Category category;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "product"
    )
    private Set<ProductSku> skus = new HashSet<>();

    @PrePersist
    @PreUpdate
    private void validateProductType() {

        if (productType == ProductType.SINGLE) {
            if (skus.size() != 1) {
                throw new IllegalStateException(
                        "SINGLE product must have exactly one SKU"
                );
            }
        }

        if (productType == ProductType.VARIANT) {
            if (skus.isEmpty()) {
                throw new IllegalStateException(
                        "VARIANT product must have at least one SKU"
                );
            }
        }
    }

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "product"
    )
    private Set<ProductGalleryImage> productGalleryImages = new HashSet<>();

}

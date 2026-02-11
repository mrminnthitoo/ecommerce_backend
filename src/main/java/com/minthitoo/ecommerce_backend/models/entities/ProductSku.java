package com.minthitoo.ecommerce_backend.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_skus")
public class ProductSku extends Base{

    @Column(
            length = 20
    )
    private String sku;

    @Column
    private Integer purchasePrice = 0;

    @Column(
            nullable = false
    )
    private Integer sellingPrice;

    @Column
    private String variantImage;

    @Column(
            nullable = false
    )
    private Integer stock;

    @Column
    private Boolean status = Boolean.TRUE;

    @Column
    private Integer weight = 0;

    @Column
    private Integer length = 0;

    @Column
    private Integer breadth = 0;

    @Column
    private Integer height = 0;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(
            name = "product_id",
            nullable = false
    )
    private Product product;

}

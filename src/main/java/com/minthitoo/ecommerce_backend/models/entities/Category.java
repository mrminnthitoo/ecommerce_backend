package com.minthitoo.ecommerce_backend.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends Base{

    @Column(length = 200)
    private String name;

    @Column(unique = true)
    private String slug;

    @Column
    private String image;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "category"
    )
    private Set<Product> products;

}

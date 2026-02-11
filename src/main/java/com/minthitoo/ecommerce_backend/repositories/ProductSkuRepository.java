package com.minthitoo.ecommerce_backend.repositories;

import com.minthitoo.ecommerce_backend.models.entities.ProductSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSkuRepository extends JpaRepository<ProductSku, Long> {
}

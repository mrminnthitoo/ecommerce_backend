package com.minthitoo.ecommerce_backend.services;

import com.minthitoo.ecommerce_backend.commons.exception.NotFoundException;
import com.minthitoo.ecommerce_backend.models.dtos.AdminProductDto;
import com.minthitoo.ecommerce_backend.models.dtos.AdminProductQueryDto;
import com.minthitoo.ecommerce_backend.models.dtos.PageRequestDto;
import org.springframework.data.domain.Page;

public interface ProductService {

    Page<AdminProductQueryDto> findAllProducts(PageRequestDto pageRequestDto);
    AdminProductDto findProductById(Long id) throws NotFoundException;
    AdminProductDto createProduct(AdminProductDto adminProductDto) throws NotFoundException;

}

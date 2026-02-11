package com.minthitoo.ecommerce_backend.services;

import com.minthitoo.ecommerce_backend.commons.exception.NotFoundException;
import com.minthitoo.ecommerce_backend.models.dtos.AdminProductImageDto;
import com.minthitoo.ecommerce_backend.models.dtos.PageRequestDto;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface ProductImageService {

    Page<AdminProductImageDto> findAllProductImages(PageRequestDto pageRequestDto);
    AdminProductImageDto uploadProductImage(AdminProductImageDto adminProductImageDto);
    List<AdminProductImageDto> uploadProductImages(List<AdminProductImageDto> adminProductImageDtos);
    byte[] findImageByName(String imageName) throws IOException, NotFoundException;
}

package com.minthitoo.ecommerce_backend.services;

import com.minthitoo.ecommerce_backend.commons.exception.NotFoundException;
import com.minthitoo.ecommerce_backend.models.dtos.AdminCategoryDto;

import java.util.List;

public interface CategoryService {

    List<AdminCategoryDto> adminGetCategories();

    AdminCategoryDto saveCategory(AdminCategoryDto adminCategoryDto);
    AdminCategoryDto updateCategory(AdminCategoryDto adminCategoryDto) throws NotFoundException;
    AdminCategoryDto deleteCategory(Long id) throws NotFoundException;

}

package com.minthitoo.ecommerce_backend.controllers.api.admin.implememtations;

import com.minthitoo.ecommerce_backend.commons.exception.NotFoundException;
import com.minthitoo.ecommerce_backend.commons.exception.ValidationException;
import com.minthitoo.ecommerce_backend.commons.response.APIResponse;
import com.minthitoo.ecommerce_backend.commons.response.RESTResponser;
import com.minthitoo.ecommerce_backend.controllers.api.admin.AdminCategoryController;
import com.minthitoo.ecommerce_backend.models.dtos.AdminCategoryDto;
import com.minthitoo.ecommerce_backend.models.dtos.CategoryCreationDto;
import com.minthitoo.ecommerce_backend.services.CategoryService;
import com.minthitoo.ecommerce_backend.services.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class AdminCategoryControllerImplememtation implements AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private APIResponse apiResponse;

    @Override
    public ResponseEntity<RESTResponser> findAllCategories() {
        List<AdminCategoryDto> adminCategories = this.categoryService.adminGetCategories();
        return this.apiResponse.successResponse(
                HttpStatus.OK,
                "all categories",
                adminCategories
        );
    }

    @Override
    public ResponseEntity<RESTResponser> createCategory(CategoryCreationDto categoryCreationDto, BindingResult result) throws ValidationException, IOException {
        if (result.hasErrors()) {
            throw new ValidationException(
                    "validation failed.",
                    result.getFieldErrors()
            );
        }else{
            AdminCategoryDto adminCategoryDto = new AdminCategoryDto();
            adminCategoryDto.setName(categoryCreationDto.getName());
            adminCategoryDto.setSlug(categoryCreationDto.getSlug());
            if(categoryCreationDto.getImage().isEmpty()){
                adminCategoryDto.setImage(null);
            }else {
                String image = this.imageUploadService.uploadImage(categoryCreationDto.getImage());
                adminCategoryDto.setImage(image);
            }
            AdminCategoryDto insertedCategory = this.categoryService.saveCategory(adminCategoryDto);
            return this.apiResponse.successResponse(
                    HttpStatus.OK,
                    "category",
                    insertedCategory
            );
        }
    }

    @Override
    public ResponseEntity<RESTResponser> updateCategory(Long id, CategoryCreationDto categoryCreationDto, BindingResult result) throws ValidationException, IOException, NotFoundException {
        if (result.hasErrors()) {
            throw new ValidationException(
                    "validation failed.",
                    result.getFieldErrors()
            );
        }else{
            AdminCategoryDto adminCategoryDto = new AdminCategoryDto();
            adminCategoryDto.setId(id);
            adminCategoryDto.setName(categoryCreationDto.getName());
            adminCategoryDto.setSlug(categoryCreationDto.getSlug());
            if(categoryCreationDto.getImage().isEmpty()){
                adminCategoryDto.setImage(null);
            }else {
                String image = this.imageUploadService.uploadImage(categoryCreationDto.getImage());
                adminCategoryDto.setImage(image);
            }
            AdminCategoryDto updatedCategory = this.categoryService.updateCategory(adminCategoryDto);
            return this.apiResponse.successResponse(
                    HttpStatus.OK,
                    "category updated successfully.",
                    updatedCategory
            );
        }
    }

    @Override
    public ResponseEntity<RESTResponser> deleteCategory(Long id) throws NotFoundException {
        AdminCategoryDto deletedCategory = this.categoryService.deleteCategory(id);
        return this.apiResponse.successResponse(
                HttpStatus.OK,
                "category deleted successfully.",
                deletedCategory
        );
    }
}

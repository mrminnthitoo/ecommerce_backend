package com.minthitoo.ecommerce_backend.controllers.api.admin;

import com.minthitoo.ecommerce_backend.commons.exception.NotFoundException;
import com.minthitoo.ecommerce_backend.commons.exception.ValidationException;
import com.minthitoo.ecommerce_backend.commons.response.RESTResponser;
import com.minthitoo.ecommerce_backend.models.dtos.CategoryCreationDto;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/api/admin/categories")
public interface AdminCategoryController {

    @GetMapping
    ResponseEntity<RESTResponser> findAllCategories();

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    ResponseEntity<RESTResponser> createCategory(@Valid @ModelAttribute CategoryCreationDto categoryCreationDto, BindingResult result) throws ValidationException, IOException;

    @PutMapping("/{id}")
    ResponseEntity<RESTResponser> updateCategory(
            @PathVariable Long id,
            @Valid @ModelAttribute CategoryCreationDto categoryCreationDto, BindingResult result
    ) throws ValidationException, IOException, NotFoundException;

    @DeleteMapping("/{id}")
    ResponseEntity<RESTResponser> deleteCategory(@PathVariable Long id) throws NotFoundException;
}

package com.minthitoo.ecommerce_backend.controllers.api.admin;

import com.minthitoo.ecommerce_backend.commons.exception.ValidationException;
import com.minthitoo.ecommerce_backend.commons.response.RESTResponser;
import com.minthitoo.ecommerce_backend.models.dtos.ProductImageCreationDto;
import com.minthitoo.ecommerce_backend.utils.enums.SortType;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/admin/images")
public interface AdminImageController {

    @GetMapping()
    ResponseEntity<RESTResponser> findAllImages(
            @RequestParam(value = "page", defaultValue = "0") Integer pageNo,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sortColumn", defaultValue = "id") String sortColumn,
            @RequestParam(value = "sortType", defaultValue = "DESC") SortType sortType
    );

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<RESTResponser> uploadImages(
            @Validated @ModelAttribute ProductImageCreationDto imageCreationDto,
            BindingResult result
            ) throws ValidationException, IOException;

}

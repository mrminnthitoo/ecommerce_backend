package com.minthitoo.ecommerce_backend.controllers.api.admin.implememtations;

import com.minthitoo.ecommerce_backend.commons.exception.ValidationException;
import com.minthitoo.ecommerce_backend.commons.response.APIResponse;
import com.minthitoo.ecommerce_backend.commons.response.RESTResponser;
import com.minthitoo.ecommerce_backend.controllers.api.admin.AdminImageController;
import com.minthitoo.ecommerce_backend.models.dtos.AdminProductImageDto;
import com.minthitoo.ecommerce_backend.models.dtos.PageRequestDto;
import com.minthitoo.ecommerce_backend.models.dtos.ProductImageCreationDto;
import com.minthitoo.ecommerce_backend.services.ImageUploadService;
import com.minthitoo.ecommerce_backend.services.ProductImageService;
import com.minthitoo.ecommerce_backend.utils.enums.SortType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class AdminImageControllerImplementation implements AdminImageController {

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private APIResponse apiResponse;

    @Override
    public ResponseEntity<RESTResponser> findAllImages(
            Integer pageNo,
            Integer pageSize,
            String sortColumn,
            SortType sortType
    ) {
        PageRequestDto pageRequestDto = new PageRequestDto(pageNo, pageSize, sortColumn, sortType);
        Page<AdminProductImageDto> images = this.productImageService.findAllProductImages(pageRequestDto);
        return this.apiResponse.successResponse(
                HttpStatus.OK,
                "product images",
                images
        );
    }

    @Override
    public ResponseEntity<RESTResponser> uploadImages(ProductImageCreationDto imageCreationDto, BindingResult result) throws ValidationException, IOException {
        log.info(imageCreationDto.toString());
        if (result.hasErrors()) {
            throw new ValidationException(
                    "validation failed.",
                    result.getFieldErrors()
            );
        }else{
            List<AdminProductImageDto> productImageDtos = new ArrayList<>();
            for (MultipartFile file : imageCreationDto.getImages()){

                String imageName = this.imageUploadService.uploadImage(file);

                AdminProductImageDto productImageDto = new AdminProductImageDto();
                productImageDto.setOriginalName(file.getOriginalFilename());
                productImageDto.setName(imageName);

                productImageDtos.add(productImageDto);

            }
            List<AdminProductImageDto> uploadedImages = this.productImageService.uploadProductImages(productImageDtos);
            return this.apiResponse.successResponse(
                    HttpStatus.CREATED,
                    "images uploaded successfully.",
                    uploadedImages
            );
        }
    }
}

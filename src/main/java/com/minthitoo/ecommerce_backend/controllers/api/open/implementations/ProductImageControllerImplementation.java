package com.minthitoo.ecommerce_backend.controllers.api.open.implementations;

import com.minthitoo.ecommerce_backend.commons.exception.NotFoundException;
import com.minthitoo.ecommerce_backend.commons.response.APIResponse;
import com.minthitoo.ecommerce_backend.controllers.api.open.ProductImageController;
import com.minthitoo.ecommerce_backend.services.ImageUploadService;
import com.minthitoo.ecommerce_backend.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ProductImageControllerImplementation implements ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private APIResponse apiResponse;

    @Override
    public ResponseEntity<byte[]> getImage(String imageName) throws IOException, NotFoundException {
        byte[] image = this.productImageService.findImageByName(imageName);
        return this.apiResponse.successImageResponse(
                this.imageUploadService.getMediaType(imageName),
                image
        );
    }
}

package com.minthitoo.ecommerce_backend.commons.validations.implementations;


import com.minthitoo.ecommerce_backend.commons.validations.ImageList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
public class ImageListValidator implements ConstraintValidator<ImageList, List<MultipartFile>> {

    boolean isValid = true;

    @Override
    public boolean isValid(List<MultipartFile> images, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Validating image list");
        if (images == null || images.isEmpty()) {
            return false;
        }

        for (MultipartFile file : images) {
            if (file == null || file.isEmpty()) return false;
            String contentType = file.getContentType();
            assert contentType != null;
            if(!(contentType.equalsIgnoreCase("image/jpeg") || contentType.equalsIgnoreCase("image/png"))){
                isValid = false;
            }
        }
        return isValid;

    }
}

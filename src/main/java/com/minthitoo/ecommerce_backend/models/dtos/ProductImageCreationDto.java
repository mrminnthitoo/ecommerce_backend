package com.minthitoo.ecommerce_backend.models.dtos;

import com.minthitoo.ecommerce_backend.commons.validations.ImageList;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ProductImageCreationDto {

    @NotNull
    @ImageList
    List<MultipartFile> images;

}

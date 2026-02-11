package com.minthitoo.ecommerce_backend.models.dtos;

import com.minthitoo.ecommerce_backend.commons.validations.ImageFile;
import com.minthitoo.ecommerce_backend.commons.validations.UniqueSlug;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CategoryCreationDto {

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    @NotBlank
    @UniqueSlug
    private String slug;

    @ImageFile
    private MultipartFile image;
}

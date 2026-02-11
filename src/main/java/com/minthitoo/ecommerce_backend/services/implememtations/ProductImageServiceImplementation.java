package com.minthitoo.ecommerce_backend.services.implememtations;

import com.minthitoo.ecommerce_backend.commons.exception.NotFoundException;
import com.minthitoo.ecommerce_backend.models.dtos.AdminProductImageDto;
import com.minthitoo.ecommerce_backend.models.dtos.PageRequestDto;
import com.minthitoo.ecommerce_backend.models.dtos.ProductImageCreationDto;
import com.minthitoo.ecommerce_backend.models.entities.ProductImage;
import com.minthitoo.ecommerce_backend.repositories.ProductImageRepository;
import com.minthitoo.ecommerce_backend.services.ImageUploadService;
import com.minthitoo.ecommerce_backend.services.ProductImageService;
import com.minthitoo.ecommerce_backend.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductImageServiceImplementation implements ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private Mapper mapper;

    @Override
    public Page<AdminProductImageDto> findAllProductImages(PageRequestDto pageRequestDto) {
        Pageable pageable = new PageRequestDto().getPage(pageRequestDto);
        Page<ProductImage> productImages = productImageRepository.findAll(pageable);
        return productImages.map(image->{
            return this.mapper.map(image, AdminProductImageDto.class);
        });
    }

    @Override
    public AdminProductImageDto uploadProductImage(AdminProductImageDto adminProductImageDto) {
        ProductImage productImage = new ProductImage();
        productImage.setName(adminProductImageDto.getName());
        productImage.setOriginalName(adminProductImageDto.getOriginalName());

        ProductImage uploadedProductImage = productImageRepository.save(productImage);
        return this.mapper.map(uploadedProductImage, AdminProductImageDto.class);
    }

    @Override
    public List<AdminProductImageDto> uploadProductImages(List<AdminProductImageDto> adminProductImageDtos) {
        List<ProductImage> productImages = new ArrayList<>();

        for (AdminProductImageDto adminProductImageDto : adminProductImageDtos) {
            ProductImage productImage = new ProductImage();
            productImage.setName(adminProductImageDto.getName());
            productImage.setOriginalName(adminProductImageDto.getOriginalName());
            productImages.add(productImage);
        }
        List<ProductImage> uploadedProductImages = productImageRepository.saveAll(productImages);
        return this.mapper.mapList(uploadedProductImages, AdminProductImageDto.class);
    }

    @Override
    public byte[] findImageByName(String imageName) throws IOException, NotFoundException {

        Optional<byte[]> imageResult = this.imageUploadService.getImage(imageName);
        if (imageResult.isPresent()) {
            return imageResult.get();
        }else {
            throw new NotFoundException(
                    "image not found",
                    "imageName",
                    "image name " + imageName + " not found."
            );
        }

    }
}

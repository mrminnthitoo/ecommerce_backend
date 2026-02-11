package com.minthitoo.ecommerce_backend.services;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ImageUploadService {

    String uploadImage(MultipartFile file) throws IOException;
    List<String> uploadImages(List<MultipartFile> files) throws IOException;
    Optional<byte[]> getImage(String fileName) throws IOException;
    MediaType getMediaType(String image);

}

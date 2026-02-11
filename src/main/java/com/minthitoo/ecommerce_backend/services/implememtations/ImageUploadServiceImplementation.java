package com.minthitoo.ecommerce_backend.services.implememtations;

import com.minthitoo.ecommerce_backend.services.ImageUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ImageUploadServiceImplementation implements ImageUploadService {

    @Value("${image.root-dir}")
    String PARENT_DIR;

    private static final String userDir = System.getProperty("user.home") + "\\Pictures";

    @Override
    public String uploadImage(MultipartFile file) throws IOException {

        String path = userDir + PARENT_DIR;

        String contentType = file.getContentType();

        String extension = "";

        assert contentType != null;
        if (contentType.equalsIgnoreCase("image/png")){
            extension = ".png";
        }else{
            extension = ".jpg";
        }

        String fileName = UUID.randomUUID() + extension;
        Path filePath = Paths.get(path + "\\" + fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        return fileName;

    }

    @Override
    public List<String> uploadImages(List<MultipartFile> files) throws IOException {
        List<String> images = new ArrayList<>();
        for (MultipartFile file : files) {
            String imageName = this.uploadImage(file);
            images.add(imageName);
        }
        return images;
    }

    @Override
    public Optional<byte[]> getImage(String fileName) throws IOException {
        String path = userDir + PARENT_DIR;
        Path filePath = Paths.get(path + "\\", fileName);
        if (filePath.toFile().exists()){
            return Optional.of(Files.readAllBytes(filePath));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public MediaType getMediaType(String image) {
        Map<String, MediaType> mediaTypes = new HashMap<>();
        mediaTypes.put(".jpg", MediaType.IMAGE_JPEG);
        mediaTypes.put(".jpeg", MediaType.IMAGE_JPEG);
        mediaTypes.put(".png", MediaType.IMAGE_PNG);

        int lastIndex = image.lastIndexOf(".");
        String extension = image.substring(lastIndex);
        return mediaTypes.get(extension);
    }
}

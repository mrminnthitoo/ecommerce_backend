package com.minthitoo.ecommerce_backend.controllers.api.open;

import com.minthitoo.ecommerce_backend.commons.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/api/products/images")
public interface ProductImageController {

    @GetMapping("{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException, NotFoundException;

}

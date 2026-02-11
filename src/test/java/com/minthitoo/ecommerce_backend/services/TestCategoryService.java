package com.minthitoo.ecommerce_backend.services;

import com.minthitoo.ecommerce_backend.models.dtos.AdminCategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCategoryService {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testAdminGetAllCategories() {
        List<AdminCategoryDto> categories = this.categoryService.adminGetCategories();
        for(AdminCategoryDto category : categories) {
            log.info("AdminCategoryDto: {}", category);
        }
        assertFalse(categories.isEmpty());
    }

}

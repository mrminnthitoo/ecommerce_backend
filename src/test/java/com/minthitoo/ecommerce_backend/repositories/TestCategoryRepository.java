package com.minthitoo.ecommerce_backend.repositories;

import com.minthitoo.ecommerce_backend.models.entities.Category;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCategoryRepository {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void insertCategoryTest(){
        Category category = new Category();
        category.setName("Electronics");
        category.setSlug("electronics");

        Category insertedCategory = this.categoryRepository.save(category);
        assertEquals("Electronics", insertedCategory.getName());
    }

}

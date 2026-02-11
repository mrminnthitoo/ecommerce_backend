package com.minthitoo.ecommerce_backend.commons.validations.implementations;

import com.minthitoo.ecommerce_backend.commons.validations.UniqueSlug;
import com.minthitoo.ecommerce_backend.models.entities.Category;
import com.minthitoo.ecommerce_backend.repositories.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UniqueSlugValidator implements ConstraintValidator<UniqueSlug, String> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean isValid(String slug, ConstraintValidatorContext context) {
        if (slug == null) return true;
        return !categoryRepository.existsBySlug(slug);
    }
}

package com.minthitoo.ecommerce_backend.services.implememtations;

import com.minthitoo.ecommerce_backend.commons.exception.NotFoundException;
import com.minthitoo.ecommerce_backend.models.dtos.AdminCategoryDto;
import com.minthitoo.ecommerce_backend.models.entities.Category;
import com.minthitoo.ecommerce_backend.repositories.CategoryRepository;
import com.minthitoo.ecommerce_backend.services.CategoryService;
import com.minthitoo.ecommerce_backend.utils.Mapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Mapper mapper;

    @Transactional
    @Override
    public List<AdminCategoryDto> adminGetCategories() {
        List<Category> categories = categoryRepository.findAll(this.getSort());
        return this.mapper.mapList(categories, AdminCategoryDto.class);
    }

    @Override
    public AdminCategoryDto saveCategory(AdminCategoryDto adminCategoryDto) {
        Category category = new Category();
        category.setName(adminCategoryDto.getName());
        category.setSlug(adminCategoryDto.getSlug());
        if (adminCategoryDto.getImage() != null) {
            category.setImage(adminCategoryDto.getImage());
        }
        Category savedCategory = this.categoryRepository.save(category);
        return this.mapper.map(savedCategory, AdminCategoryDto.class);
    }

    @Override
    public AdminCategoryDto updateCategory(AdminCategoryDto adminCategoryDto) throws NotFoundException {
        Optional<Category> categoryResult = this.categoryRepository.findById(adminCategoryDto.getId());
        if (categoryResult.isPresent()) {
            Category category = categoryResult.get();

            category.setName(adminCategoryDto.getName());
            category.setSlug(adminCategoryDto.getSlug());
            if (adminCategoryDto.getImage() != null) {
                category.setImage(adminCategoryDto.getImage());
            }
            Category savedCategory = this.categoryRepository.save(category);
            return this.mapper.map(savedCategory, AdminCategoryDto.class);
        }else{
            throw new NotFoundException(
                    "category not found.",
                    "id",
                    "category id " + adminCategoryDto.getId() + " not found."
            );
        }
    }

    @Override
    public AdminCategoryDto deleteCategory(Long id) throws NotFoundException {
        Optional<Category> categoryResult = this.categoryRepository.findById(id);
        if (categoryResult.isPresent()) {
            this.categoryRepository.delete(categoryResult.get());
            return this.mapper.map(categoryResult.get(), AdminCategoryDto.class);
        }else {
            throw new NotFoundException(
                    "category not found.",
                    "id",
                    "category id " + id + " not found."
            );
        }
    }

    private Sort getSort() {
        return Sort.by(Sort.Direction.ASC, "name");
    }
}

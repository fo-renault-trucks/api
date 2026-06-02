package com.rt_fo.api.category.service;

import com.rt_fo.api.category.dto.CategoryWithReferencedDto;
import com.rt_fo.api.category.entity.Category;
import com.rt_fo.api.category.exception.CategoryNotFoundException;
import com.rt_fo.api.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryWithReferencedDto> getCategories() {
        return categoryRepository.findAllWithReferenced();
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category createCategory(String name) {
        Category category = new Category(name);

        return categoryRepository.save(category);
    }

    public Category updateCategory(Integer id, String name) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        category.setName(name);

        return categoryRepository.save(category);
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}

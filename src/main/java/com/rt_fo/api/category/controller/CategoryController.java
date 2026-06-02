package com.rt_fo.api.category.controller;

import com.rt_fo.api.category.dto.CategoryDto;
import com.rt_fo.api.category.dto.CategoryEditionRequest;
import com.rt_fo.api.category.dto.CategoryWithReferencedDto;
import com.rt_fo.api.category.entity.Category;
import com.rt_fo.api.category.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryWithReferencedDto>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryEditionRequest request) {
        Category category = categoryService.createCategory(request.name());

        return ResponseEntity.ok(CategoryDto.fromEntity(category));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Integer id, @RequestBody CategoryEditionRequest request) {
        Category category = categoryService.updateCategory(id, request.name());

        return ResponseEntity.ok(CategoryDto.fromEntity(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);

        return ResponseEntity.noContent()
                .build();
    }
}

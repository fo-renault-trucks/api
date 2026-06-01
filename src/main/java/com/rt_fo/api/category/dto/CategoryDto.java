package com.rt_fo.api.category.dto;

import com.rt_fo.api.category.entity.Category;

public record CategoryDto(Integer id, String name) {

    public static CategoryDto fromEntity(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}

package com.rt_fo.api.category.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends IllegalArgumentException {

    public CategoryNotFoundException(Integer id) {
        super("Category with id '%d' not found".formatted(id));
    }
}

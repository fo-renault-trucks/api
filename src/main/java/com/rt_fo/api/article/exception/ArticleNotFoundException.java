package com.rt_fo.api.article.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ArticleNotFoundException extends IllegalArgumentException {

    public ArticleNotFoundException(Long id) {
        super("Article with id '%d' not found".formatted(id));
    }
}

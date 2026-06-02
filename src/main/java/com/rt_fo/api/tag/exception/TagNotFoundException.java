package com.rt_fo.api.tag.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TagNotFoundException extends IllegalArgumentException {

    public TagNotFoundException(List<Integer> ids) {
        super("Tags with ids '%s' not found".formatted(ids));
    }
}

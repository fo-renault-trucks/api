package com.rt_fo.api.factory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FactoryNotFoundException extends IllegalArgumentException {

    public FactoryNotFoundException(List<Integer> ids) {
        super("Factories with ids '%s' not found".formatted(ids));
    }
}

package com.rt_fo.api.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailInUseException extends IllegalArgumentException {

    public EmailInUseException(String email) {
        super("Email '%s' is already taken".formatted(email));
    }
}

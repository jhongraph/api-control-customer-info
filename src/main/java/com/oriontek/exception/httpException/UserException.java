package com.oriontek.exception.httpException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}

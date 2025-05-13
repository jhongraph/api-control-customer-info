package com.gateway.exceptions.httpException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedHttpMethodException extends RuntimeException {
    public UnsupportedHttpMethodException(String message) {
        super(message);
    }
}

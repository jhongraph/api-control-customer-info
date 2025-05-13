package com.gateway.exceptions.httpException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingHeadersException extends RuntimeException {
    public MissingHeadersException(String message) {
        super(message);
    }
}

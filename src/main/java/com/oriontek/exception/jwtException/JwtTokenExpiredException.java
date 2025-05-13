package com.gateway.exceptions.jwtException;

public class JwtTokenExpiredException extends RuntimeException {
    public JwtTokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}

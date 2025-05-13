package com.oriontek.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt")
@Slf4j
public class JwtConfig {

    private String log1;
    private String secret_key;
    private Long access_token_expiration;
    private Long refresh_token_expiration;
}
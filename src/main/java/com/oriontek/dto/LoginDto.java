package com.oriontek.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginDto {

    @JsonProperty("username")
    String username;
    @JsonProperty("password")
    String password;
}

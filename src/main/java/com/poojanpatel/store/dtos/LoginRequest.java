package com.poojanpatel.store.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message =  "email is required")
    public String email;
    @NotBlank(message =  "password is required")
    public String password;
}

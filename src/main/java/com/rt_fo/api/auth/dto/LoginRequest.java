package com.rt_fo.api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "email is mandatory") @Email(message = "email must be a valid email") String email,
        @NotBlank(message = "password is mandatory") String password
) {

}

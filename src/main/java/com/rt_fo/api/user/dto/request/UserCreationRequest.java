package com.rt_fo.api.user.dto.request;

import com.rt_fo.api.user.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserCreationRequest(
        @NotBlank(message = "email is mandatory") @Email(message = "email must be a valid email") String email,
        @NotBlank(message = "password is mandatory")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$", message = "password must be at least 8 characters long and contain at least one letter and one number")
        String password,
        @NotBlank(message = "firstName is mandatory") String firstName,
        @NotBlank(message = "lastName is mandatory") String lastName,
        @NotNull(message = "role is mandatory") Role role
) {

}

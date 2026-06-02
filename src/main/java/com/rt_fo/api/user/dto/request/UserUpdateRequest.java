package com.rt_fo.api.user.dto.request;

import com.rt_fo.api.user.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserUpdateRequest(
        @NotBlank(message = "firstName is mandatory") String firstName,
        @NotBlank(message = "lastName is mandatory") String lastName,
        @NotNull(message = "role is mandatory") Role role
) {

}

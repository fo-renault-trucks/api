package com.rt_fo.api.auth.dto;

import com.rt_fo.api.user.entity.Role;
import com.rt_fo.api.user.entity.User;

public record AuthenticatedUserDto(Long id, String firstName, String lastName, String email, Role role) {

    public static AuthenticatedUserDto fromEntity(User user) {
        return new AuthenticatedUserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }
}

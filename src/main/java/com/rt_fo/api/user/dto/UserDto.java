package com.rt_fo.api.user.dto;

import com.rt_fo.api.user.entity.Role;
import com.rt_fo.api.user.entity.User;

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        Role role
) {
    public static UserDto fromEntity(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }
}

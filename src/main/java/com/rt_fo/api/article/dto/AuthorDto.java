package com.rt_fo.api.article.dto;

import com.rt_fo.api.user.entity.User;

public record AuthorDto(Long id, String firstName, String lastName) {

    public static AuthorDto fromEntity(User author) {
        return new AuthorDto(author.getId(), author.getFirstName(), author.getLastName());
    }
}

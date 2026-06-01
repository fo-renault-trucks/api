package com.rt_fo.api.tag.dto;

import com.rt_fo.api.tag.entity.Tag;

public record TagDto(Integer id, String name) {

    public static TagDto fromEntity(Tag tag) {
        return new TagDto(tag.getId(), tag.getName());
    }
}

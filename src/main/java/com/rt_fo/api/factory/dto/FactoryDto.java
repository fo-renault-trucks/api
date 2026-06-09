package com.rt_fo.api.factory.dto;

import com.rt_fo.api.factory.entity.Factory;

public record FactoryDto(Integer id, String name) {

    public static FactoryDto fromEntity(Factory factory) {
        return new FactoryDto(factory.getId(), factory.getName());
    }
}

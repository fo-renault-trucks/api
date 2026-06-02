package com.rt_fo.api.article.dto;

import com.rt_fo.api.article.entity.Article;
import com.rt_fo.api.article.entity.ArticleStatus;

import java.time.Instant;

public record ArticleDto(
        Long id,
        String title,
        ArticleStatus status,
        Integer categoryId,
        Long authorId,
        Instant updatedAt
) {
    public static ArticleDto fromEntity(Article article) {
        return new ArticleDto(
                article.getId(),
                article.getTitle(),
                article.getStatus(),
                article.getCategory().getId(),
                article.getAuthor().getId(),
                article.getUpdatedAt()
        );
    }
}

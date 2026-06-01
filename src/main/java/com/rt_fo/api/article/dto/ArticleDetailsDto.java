package com.rt_fo.api.article.dto;

import com.rt_fo.api.article.entity.Article;
import com.rt_fo.api.article.entity.ArticleStatus;
import com.rt_fo.api.tag.entity.Tag;

import java.time.Instant;
import java.util.List;

public record ArticleDetailsDto(
        Long id,
        String title,
        ArticleStatus status,
        String subTitle,
        Integer categoryId,
        List<Integer> tagIds,
        AuthorDto author,
        Boolean authorVisible,
        String content,
        Instant createdAt,
        Instant updatedAt
) {
    public static ArticleDetailsDto fromEntity(Article article) {
        return new ArticleDetailsDto(
                article.getId(),
                article.getTitle(),
                article.getStatus(),
                article.getSubTitle(),
                article.getCategory().getId(),
                article.getTags().stream().map(Tag::getId).toList(),
                AuthorDto.fromEntity(article.getAuthor()),
                article.isAuthorVisible(),
                article.getContent(),
                article.getCreatedAt(),
                article.getUpdatedAt()
        );
    }
}

package com.rt_fo.api.article.dto.request;

import com.rt_fo.api.article.entity.ArticleStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ArticleCreationRequest(
        @NotBlank(message = "title is mandatory") String title,
        @NotNull(message = "status is mandatory") ArticleStatus status,
        @NotBlank(message = "subTitle is mandatory") String subTitle,
        @NotNull(message = "categoryId is mandatory") Integer categoryId,
        @NotNull(message = "tagIds is mandatory") List<Integer> tagIds,
        @NotEmpty(message = "factoryIds is mandatory") List<Integer> factoryIds,
        @NotBlank(message = "content is mandatory") String content,
        @NotNull(message = "authorVisible is mandatory") Boolean authorVisible
) implements ArticleRequest {

}

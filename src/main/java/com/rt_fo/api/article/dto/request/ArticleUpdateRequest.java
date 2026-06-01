package com.rt_fo.api.article.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ArticleUpdateRequest(
        @NotBlank(message = "title is mandatory") String title,
        @NotBlank(message = "subTitle is mandatory") String subTitle,
        @NotNull(message = "categoryId is mandatory") Integer categoryId,
        @NotNull(message = "tagIds is mandatory") List<Integer> tagIds,
        @NotNull(message = "authorVisible is mandatory") Boolean authorVisible,
        @NotBlank(message = "content is mandatory") String content
) implements ArticleRequest {

}

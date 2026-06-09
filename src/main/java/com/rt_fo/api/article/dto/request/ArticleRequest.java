package com.rt_fo.api.article.dto.request;

import java.util.List;

public interface ArticleRequest {

    String title();

    String subTitle();

    Integer categoryId();

    List<Integer> tagIds();

    List<Integer> factoryIds();

    Boolean authorVisible();

    String content();
}

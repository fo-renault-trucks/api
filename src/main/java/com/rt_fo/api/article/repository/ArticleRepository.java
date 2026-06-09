package com.rt_fo.api.article.repository;

import com.rt_fo.api.article.dto.ArticleDto;
import com.rt_fo.api.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("""
        SELECT new com.rt_fo.api.article.dto.ArticleDto(
            a.id,
            a.title,
            a.status,
            a.category.id,
            a.author.id,
            a.updatedAt
        )
        FROM Article a
    """)
    List<ArticleDto> findAllDtos();

    @Query("""
        SELECT a
        FROM Article a
        LEFT JOIN FETCH a.tags
        LEFT JOIN FETCH a.factories
        WHERE a.id = :id
    """)
    Optional<Article> findByIdWithTagsAndFactories(@Param("id") Long id);
}

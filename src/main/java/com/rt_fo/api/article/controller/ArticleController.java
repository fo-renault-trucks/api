package com.rt_fo.api.article.controller;

import com.rt_fo.api.article.dto.ArticleDetailsDto;
import com.rt_fo.api.article.dto.ArticleDto;
import com.rt_fo.api.article.dto.request.ArticleCreationRequest;
import com.rt_fo.api.article.dto.request.ArticleUpdateRequest;
import com.rt_fo.api.article.entity.Article;
import com.rt_fo.api.article.service.ArticleService;
import com.rt_fo.api.security.jwt.JwtService;
import com.rt_fo.api.user.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final JwtService jwtService;

    public ArticleController(ArticleService articleService, JwtService jwtService) {
        this.articleService = articleService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public ResponseEntity<List<ArticleDto>> getArticles() {
        List<ArticleDto> articles = articleService.getArticles();

        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDetailsDto> getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id)
                .map(article -> ResponseEntity.ok(ArticleDetailsDto.fromEntity(article)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<ArticleDto> createArticle(
            @RequestBody @Valid ArticleCreationRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        User user = jwtService.getUserFromJwt(jwt);

        Article article = articleService.createArticle(request, user);

        return ResponseEntity.ok(ArticleDto.fromEntity(article));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<ArticleDetailsDto> updateArticle(
            @PathVariable Long id,
            @RequestBody @Valid ArticleUpdateRequest request
    ) {
        Article article = articleService.updateArticle(id, request);

        return ResponseEntity.ok(ArticleDetailsDto.fromEntity(article));
    }

    @PostMapping("/{id}/publish")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<ArticleDto> publishArticle(@PathVariable Long id) {
        Article article = articleService.publishArticle(id);

        return ResponseEntity.ok(ArticleDto.fromEntity(article));
    }

    @PostMapping("/{id}/unpublish")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<ArticleDto> unpublishArticle(@PathVariable Long id) {
        Article article = articleService.unpublishArticle(id);

        return ResponseEntity.ok(ArticleDto.fromEntity(article));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);

        return ResponseEntity.noContent().build();
    }
}

package com.rt_fo.api.article.service;

import com.rt_fo.api.article.dto.ArticleDto;
import com.rt_fo.api.article.dto.request.ArticleCreationRequest;
import com.rt_fo.api.article.dto.request.ArticleRequest;
import com.rt_fo.api.article.dto.request.ArticleUpdateRequest;
import com.rt_fo.api.article.entity.Article;
import com.rt_fo.api.article.entity.ArticleStatus;
import com.rt_fo.api.article.exception.ArticleNotFoundException;
import com.rt_fo.api.article.repository.ArticleRepository;
import com.rt_fo.api.category.entity.Category;
import com.rt_fo.api.category.service.CategoryService;
import com.rt_fo.api.tag.entity.Tag;
import com.rt_fo.api.tag.service.TagService;
import com.rt_fo.api.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    public ArticleService(
            ArticleRepository articleRepository,
            CategoryService categoryService,
            TagService tagService
    ) {
        this.articleRepository = articleRepository;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    public List<ArticleDto> getArticles() {
        return articleRepository.findAllDtos();
    }

    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findByIdWithTags(id);
    }

    public Article createArticle(ArticleCreationRequest request, User author) {
        Article article = new Article();

        // Set status (DRAFT/PUBLISHED)
        article.setStatus(request.status());

        fillEntity(article, request);

        // Set author
        article.setAuthor(author);

        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, ArticleUpdateRequest request) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));

        fillEntity(article, request);

        return articleRepository.save(article);
    }

    public Article publishArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));

        if (article.getStatus() == ArticleStatus.PUBLISHED) {
            return article;
        }

        article.setStatus(ArticleStatus.PUBLISHED);

        return articleRepository.save(article);
    }

    public Article unpublishArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));

        if (article.getStatus() == ArticleStatus.DRAFT) {
            return article;
        }

        article.setStatus(ArticleStatus.DRAFT);

        return articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    private void fillEntity(Article article, ArticleRequest request) {
        article.setTitle(request.title());
        article.setSubTitle(request.subTitle());

        // Set category
        Category category = categoryService.getCategoryById(request.categoryId());
        article.setCategory(category);

        // Set tags
        List<Tag> tags = tagService.getTagsById(request.tagIds());
        article.setTags(tags);

        article.setContent(request.content());
        article.setAuthorVisible(request.authorVisible());
    }
}

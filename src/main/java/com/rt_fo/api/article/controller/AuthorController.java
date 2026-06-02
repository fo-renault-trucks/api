package com.rt_fo.api.article.controller;

import com.rt_fo.api.article.dto.AuthorDto;
import com.rt_fo.api.article.service.AuthorService;
import com.rt_fo.api.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        List<User> authors = authorService.getAuthors();

        return ResponseEntity.ok(
                authors.stream()
                        .map(AuthorDto::fromEntity)
                        .toList()
        );
    }
}

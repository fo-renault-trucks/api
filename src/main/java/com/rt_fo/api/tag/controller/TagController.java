package com.rt_fo.api.tag.controller;

import com.rt_fo.api.tag.dto.TagDto;
import com.rt_fo.api.tag.dto.TagEditionRequest;
import com.rt_fo.api.tag.dto.TagWithReferencedDto;
import com.rt_fo.api.tag.entity.Tag;
import com.rt_fo.api.tag.service.TagService;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<TagWithReferencedDto>> getTags() {
        return ResponseEntity.ok(tagService.getTags());
    }

    @PostMapping
    public ResponseEntity<TagDto> createTag(@RequestBody TagEditionRequest request) {
        Tag tag = tagService.createTag(request.name());

        return ResponseEntity.ok(TagDto.fromEntity(tag));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TagDto> updateTag(@PathVariable Integer id, @RequestBody TagEditionRequest request) {
        Tag tag = tagService.updateTag(id, request.name());

        return ResponseEntity.ok(TagDto.fromEntity(tag));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Integer id) {
        tagService.deleteTag(id);

        return ResponseEntity.noContent().build();
    }
}

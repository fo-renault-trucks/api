package com.rt_fo.api.tag.service;

import com.rt_fo.api.tag.dto.TagWithReferencedDto;
import com.rt_fo.api.tag.entity.Tag;
import com.rt_fo.api.tag.exception.TagNotFoundException;
import com.rt_fo.api.tag.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<TagWithReferencedDto> getTags() {
        return tagRepository.findAllWithReferenced();
    }

    public List<Tag> getTagsById(List<Integer> ids) {
        Map<Integer, Tag> tags = tagRepository.findAllById(ids)
                .stream()
                .collect(Collectors.toMap(Tag::getId, Function.identity()));

        if (tags.size() != ids.size()) {
            List<Integer> unknownTagIds = ids
                    .stream()
                    .filter(id -> !tags.containsKey(id))
                    .toList();

            throw new TagNotFoundException(unknownTagIds);
        }

        return List.copyOf(tags.values());
    }

    public Tag createTag(String name) {
        Tag tag = new Tag(name);

        return tagRepository.save(tag);
    }

    public Tag updateTag(Integer id, String name) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new TagNotFoundException(Collections.singletonList(id)));

        tag.setName(name);

        return tagRepository.save(tag);
    }

    public void deleteTag(Integer id) {
        tagRepository.deleteById(id);
    }
}

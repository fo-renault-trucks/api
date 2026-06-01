package com.rt_fo.api.tag.repository;

import com.rt_fo.api.tag.dto.TagWithReferencedDto;
import com.rt_fo.api.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query("""
        SELECT new com.rt_fo.api.tag.dto.TagWithReferencedDto(
            t.id,
            t.name,
            CASE WHEN COUNT(a) > 0 THEN true ELSE false END
        )
        FROM Tag t
        LEFT JOIN t.articles a
        GROUP BY t.id, t.name
        ORDER BY t.name
    """)
    List<TagWithReferencedDto> findAllWithReferenced();
}

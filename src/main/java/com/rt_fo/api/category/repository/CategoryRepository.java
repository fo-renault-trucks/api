package com.rt_fo.api.category.repository;

import com.rt_fo.api.category.dto.CategoryWithReferencedDto;
import com.rt_fo.api.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("""
        SELECT new com.rt_fo.api.category.dto.CategoryWithReferencedDto(
            c.id,
            c.name,
            CASE WHEN COUNT(a) > 0 THEN true ELSE false END
        )
        FROM Category c
        LEFT JOIN c.articles a
        GROUP BY c.id, c.name
        ORDER BY c.name
    """)
    List<CategoryWithReferencedDto> findAllWithReferenced();
}

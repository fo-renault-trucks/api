package com.rt_fo.api.factory.repository;

import com.rt_fo.api.factory.dto.FactoryWithReferencedDto;
import com.rt_fo.api.factory.entity.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactoryRepository extends JpaRepository<Factory, Integer> {

    @Query("""
        SELECT new com.rt_fo.api.factory.dto.FactoryWithReferencedDto(
            f.id,
            f.name,
            CASE WHEN COUNT(a) > 0 THEN true ELSE false END
        )
        FROM Factory f
        LEFT JOIN f.articles a
        GROUP BY f.id, f.name
        ORDER BY f.name
    """)
    List<FactoryWithReferencedDto> findAllWithReferenced();
}

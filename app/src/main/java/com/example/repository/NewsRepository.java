package com.example.repository;

import com.example.model.News;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {

    @EntityGraph(attributePaths = "comments", type = EntityGraph.EntityGraphType.LOAD)
    Optional<News> findById(Long id);

    boolean existsById(Long id);

    Optional<Long> findUserIdById(Long id);
}

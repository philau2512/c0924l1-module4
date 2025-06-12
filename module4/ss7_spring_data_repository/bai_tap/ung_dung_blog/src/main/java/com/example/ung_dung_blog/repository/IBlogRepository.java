package com.example.ung_dung_blog.repository;

import com.example.ung_dung_blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBlogRepository extends JpaRepository<Blog, Long> {
    @Query("""
                SELECT b FROM Blog b
                WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(b.content) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(b.category.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    Page<Blog> searchAll(@Param("keyword") String keyword, Pageable pageable);

}

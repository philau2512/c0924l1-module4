package com.example.ajax_ung_dung_blog.service;

import com.example.ajax_ung_dung_blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService {
    List<Blog> findAll();
    Page<Blog> findAll(String searchName, Pageable pageable);
    Blog findById(Long id);
    void save(Blog blog);
    void delete(Long id);
}

package com.example.api_ung_dung_blog.controller;

import com.example.api_ung_dung_blog.config.ApiResponse;
import com.example.api_ung_dung_blog.dto.CategoryDTO;
import com.example.api_ung_dung_blog.model.Blog;
import com.example.api_ung_dung_blog.model.Category;
import com.example.api_ung_dung_blog.service.IBlogService;
import com.example.api_ung_dung_blog.service.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/blogs")
public class RestBlogController {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    // --- Get list category ---
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categoriesList = categoryService.findAll();
        if (categoriesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoriesList) {
            CategoryDTO dto = new CategoryDTO();
            BeanUtils.copyProperties(category, dto);
            categoryDTOList.add(dto);
        }

        return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
    }

    // --- Get blog list ---
    @GetMapping("")
    public ResponseEntity<List<Blog>> getBlogList() {
        List<Blog> blogList = blogService.findAll();
        if (blogList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }

    // --- Xem danh sách các bài viết của một category ---
    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<?> getBlogsByCategory(@org.springframework.web.bind.annotation.PathVariable Long categoryId) {
        Category category = categoryService.findById(categoryId);

        if (category == null) {
//            Map<String, String> response = new HashMap<>();
//            response.put("message", "ID category không tồn tại");
            return new ResponseEntity<>(new ApiResponse("ID category không tồn tại"), HttpStatus.NOT_FOUND);
        }

        List<Blog> blogList = category.getBlogs();
        if (blogList.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse("Danh mục này chưa có bài viết nào"), HttpStatus.OK);
        }
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }

    // --- Xem chi tiết một bài viết ---
    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogById(@org.springframework.web.bind.annotation.PathVariable Long id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(new ApiResponse("ID blog không tồn tại"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }
}

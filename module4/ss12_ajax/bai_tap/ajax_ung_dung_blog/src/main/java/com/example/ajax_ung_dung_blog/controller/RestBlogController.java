package com.example.ajax_ung_dung_blog.controller;

import com.example.ajax_ung_dung_blog.config.ApiResponse;
import com.example.ajax_ung_dung_blog.dto.CategoryDTO;
import com.example.ajax_ung_dung_blog.model.Blog;
import com.example.ajax_ung_dung_blog.model.Category;
import com.example.ajax_ung_dung_blog.service.IBlogService;
import com.example.ajax_ung_dung_blog.service.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
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
//    @GetMapping("")
//    public ResponseEntity<List<Blog>> getBlogList() {
//        List<Blog> blogList = blogService.findAll();
//        if (blogList.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(blogList, HttpStatus.OK);
//    }

    @GetMapping("")
    public ResponseEntity<Page<Blog>> getBlogList(
            @RequestParam(required = false, defaultValue = "") String searchName,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "3") int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);

        try {
            // Lấy trang các blog với tìm kiếm theo tên
            Page<Blog> blogPage = blogService.findAll(searchName, pageable);

            // Trả về response với status 200 (OK)
            return new ResponseEntity<>(blogPage, HttpStatus.OK);
        } catch (Exception e) {
            // Nếu có lỗi, trả về status 500 (Internal Server Error)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

package com.example.ajax_ung_dung_blog.controller;

import com.example.ajax_ung_dung_blog.config.ApiResponse;
import com.example.ajax_ung_dung_blog.dto.BlogDTO;
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

    // --- Get blog list with pagination and search ---
    @GetMapping("")
    public ResponseEntity<?> getBlogList(
            @RequestParam(required = false, defaultValue = "") String searchName,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "3") int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Blog> blogPage = blogService.findAll(searchName, pageable);

        if (blogPage.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse("No blogs found"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogPage, HttpStatus.OK);
    }

    // --- Get blogs by category ---
    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<?> getBlogsByCategory(@PathVariable Long categoryId) {
        Category category = categoryService.findById(categoryId);

        if (category == null) {
            return new ResponseEntity<>(new ApiResponse("ID category không tồn tại"), HttpStatus.NOT_FOUND);
        }

        List<Blog> blogList = category.getBlogs();
        if (blogList.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse("Danh mục này chưa có bài viết nào"), HttpStatus.OK);
        }
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }

    // --- Get blog by ID ---
    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(new ApiResponse("ID blog không tồn tại"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    // --- Create blog ---
    @PostMapping("")
    public ResponseEntity<?> createBlog(@RequestBody BlogDTO blogDTO) {
        if (blogDTO.getTitle() == null || blogDTO.getContent() == null) {
            return new ResponseEntity<>(new ApiResponse("Title and content are required"), HttpStatus.BAD_REQUEST);
        }

        Blog blog = new Blog();
        BeanUtils.copyProperties(blogDTO, blog);
        Category category = categoryService.findById(blogDTO.getCategoryId());
        if (category == null) {
            return new ResponseEntity<>(new ApiResponse("Invalid category ID"), HttpStatus.BAD_REQUEST);
        }
        blog.setCategory(category);
        blogService.save(blog);
        return new ResponseEntity<>(new ApiResponse("Blog created successfully"), HttpStatus.CREATED);
    }

    // --- Update blog ---
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable Long id, @RequestBody BlogDTO blogDTO) {
        Blog existingBlog = blogService.findById(id);
        if (existingBlog == null) {
            return new ResponseEntity<>(new ApiResponse("ID blog không tồn tại"), HttpStatus.NOT_FOUND);
        }

        if (blogDTO.getTitle() == null || blogDTO.getContent() == null) {
            return new ResponseEntity<>(new ApiResponse("Title and content are required"), HttpStatus.BAD_REQUEST);
        }

        BeanUtils.copyProperties(blogDTO, existingBlog);
        Category category = categoryService.findById(blogDTO.getCategoryId());
        if (category == null) {
            return new ResponseEntity<>(new ApiResponse("Invalid category ID"), HttpStatus.BAD_REQUEST);
        }
        existingBlog.setCategory(category);
        blogService.save(existingBlog);
        return new ResponseEntity<>(new ApiResponse("Blog updated successfully"), HttpStatus.OK);
    }

    // --- Delete blog ---
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(new ApiResponse("ID blog không tồn tại"), HttpStatus.NOT_FOUND);
        }
        blogService.delete(id);
        return new ResponseEntity<>(new ApiResponse("Blog deleted successfully"), HttpStatus.OK);
    }
}

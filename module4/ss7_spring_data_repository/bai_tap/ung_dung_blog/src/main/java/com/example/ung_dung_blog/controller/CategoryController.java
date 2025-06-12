package com.example.ung_dung_blog.controller;

import com.example.ung_dung_blog.model.Category;
import com.example.ung_dung_blog.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    // --- List ---
    @GetMapping("")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category/list";
    }

    // --- Create ---
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/create";
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    // --- Edit ---
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category/edit";
    }

    @PostMapping("/edit")
    public String editCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    // --- Delete ---
    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}

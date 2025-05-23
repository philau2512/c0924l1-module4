package com.example.hien_thi_gia_vi_sandwich.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SandwichController {
    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @GetMapping("/result")
    public String showResult() {
        return "result";
    }

    @PostMapping("/save")
    public String save(@RequestParam(value = "condiment", required = false) String[] condiments, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("condiments", condiments);
        return "redirect:/result";
    }
}

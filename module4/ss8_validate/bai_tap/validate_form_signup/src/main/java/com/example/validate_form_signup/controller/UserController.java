package com.example.validate_form_signup.controller;

import com.example.validate_form_signup.model.User;
import com.example.validate_form_signup.service.IUserService;
import com.example.validate_form_signup.validator.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping("/home")
    public String showHomePage() {
        return "user/home";
    }

    // --- Register ---
    @RequestMapping("/signup")
    public String showSignUpPage(Model model) {
        model.addAttribute("user", new User());
        return "user/signup";
    }

    @PostMapping("/signup")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "user/signup";
        }

        userService.save(user);
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:/user/result";
    }

    // --- Result ---
    @RequestMapping("/result")
    public String showResultPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        return "user/result";
    }

    // --- Login ---
    @GetMapping("/login")
    public String showLoginForm(Model model,
                                @ModelAttribute("user") User user) {
        if (user == null) {
            model.addAttribute("user", new User());
        }
        return "user/login";
    }

    @PostMapping("/toLogin")
    public String redirectToLogin(@RequestParam String email,
                                  @RequestParam String password,
                                  RedirectAttributes redirectAttributes) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:/user/login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute("user") User user,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        User found = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (found != null) {
            redirectAttributes.addFlashAttribute("user", found);
            return "redirect:/user/info";
        }
        model.addAttribute("error", "Email hoặc mật khẩu không đúng");
        return "user/login";
    }

    // --- Info ---
    @RequestMapping("/info")
    public String showInfoPage(@ModelAttribute("user") User user, Model model) {
        if (user == null || user.getEmail() == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("user", user);
        return "user/info";
    }

    // --- Logout ---
    @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Bạn đã đăng xuất thành công!");
        return "redirect:/user/login";
    }
}

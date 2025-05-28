package com.example.setting_mail.controller;

import com.example.setting_mail.model.Setting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SettingController {
    private static Setting currentSettings = new Setting();

    static {
        currentSettings.setLanguage("English");
        currentSettings.setPageSize(10);
        currentSettings.setSpamFilterEnabled(false);
        currentSettings.setSignature("Peter Paul");
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("setting", currentSettings);
        return "index";
    }

//    @GetMapping("/result")
//    public String showResult(Model model) {
//        model.addAttribute("setting", currentSettings);
//        return "result";
//    }

    @PostMapping("/save-settings")
    public String updateSettings(@ModelAttribute("setting") Setting settings, Model model) {
        currentSettings = settings;
        model.addAttribute("setting", currentSettings);
        return "result";
    }
}

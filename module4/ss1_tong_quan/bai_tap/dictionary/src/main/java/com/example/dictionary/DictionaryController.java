package com.example.dictionary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DictionaryController {
    private DictionaryService dictionaryService = new DictionaryService();

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam String word, Model model) {
        model.addAttribute("word", word);
        String result = dictionaryService.search(word);

        if (result == null) {
            model.addAttribute("meaning", "Not found");
        } else {
            model.addAttribute("meaning", result);
        }
        return "result";
    }
}

package com.example.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam("num1") double num1,
                            @RequestParam("num2") double num2,
                            @RequestParam("operator") String operator,
                            Model model) {
        double result = 0;
        String operation = "";

        switch (operator) {
            case "addition":
                result = num1 + num2;
                operation = "Addition";
                break;
            case "subtract":
                result = num1 - num2;
                operation = "Subtraction";
                break;
            case "multiply":
                result = num1 * num2;
                operation = "Multiplication";
                break;
            case "divide":
                if (num2 != 0) {
                    result = num1 / num2;
                    operation = "Division";
                } else {
                    model.addAttribute("error", "Cannot divide by zero");
                    return "index";
                }
        }

        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("result", result);
        model.addAttribute("operation", operation);

        return "index";
    }
}

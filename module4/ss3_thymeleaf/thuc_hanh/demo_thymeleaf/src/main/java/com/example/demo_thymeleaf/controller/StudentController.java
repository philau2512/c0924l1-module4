package com.example.demo_thymeleaf.controller;

import com.example.demo_thymeleaf.config.ClassCGEditor;
import com.example.demo_thymeleaf.model.ClassCG;
import com.example.demo_thymeleaf.model.Student;
import com.example.demo_thymeleaf.service.IClassService;
import com.example.demo_thymeleaf.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClassService classService;

    @Autowired
    private ClassCGEditor classCGEditor;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(ClassCG.class, classCGEditor);
    }

    @ModelAttribute("languages")// ứng dụng tạo session => bài 10
    public List<String> getAllLanguage(){
        System.out.println("---------------------languages--------------------------");
        return Arrays.asList("JAVA","JS","PHP","SQL","C++");
    }
    @GetMapping("")
    public String showList(ModelMap model){
        model.addAttribute("studentList", studentService.findAll());
        return "/students/list";
    }
    @GetMapping("/add")
    public String showFormAdd(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("classList", classService.findAll());
        return "students/add";
    }
    @PostMapping("/add")
    public String save(@ModelAttribute Student student, RedirectAttributes redirectAttributes){

        studentService.add(student);
        redirectAttributes.addFlashAttribute("mess","add success");
        return "redirect:/students";
    }
    @GetMapping("/detail")
    public String detail1(@RequestParam int id, Model model){
        // gọi service
        Student student = studentService.findById(id);
        model.addAttribute("student",student);
        return "students/detail";
    }
    @GetMapping("/detail/{id}")
    public String detail2(@PathVariable int id, Model model){
        // gọi service
        Student student = studentService.findById(id);
        model.addAttribute("student",student);
        return "students/detail";
    }
}
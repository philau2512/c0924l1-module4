package com.codegym.demo_spring_jpa.controller;

import com.codegym.demo_spring_jpa.dto.StudentDto;
import com.codegym.demo_spring_jpa.exception.DuplicateAdminName;
import com.codegym.demo_spring_jpa.model.Student;
import com.codegym.demo_spring_jpa.service.IClassService;
import com.codegym.demo_spring_jpa.service.IStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClassService classService;

    @GetMapping("")
    public String showList(@RequestParam(required = false, defaultValue = "2") int size,
                           @RequestParam(required = false,defaultValue = "0") int page,
                           @RequestParam(required = false,defaultValue = "") String searchName,
                           ModelMap model){
        Sort sort = Sort.by(Sort.Direction.ASC,"name").and(Sort.by( Sort.Direction.DESC,"gender"));
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Student> studentPage = studentService.findAll(searchName,pageable);
        model.addAttribute("studentPage", studentPage);
        model.addAttribute("searchName", searchName);
        return "/students/list";
    }

    @GetMapping("/add")
    public String showFormAdd(Model model){
        model.addAttribute("studentDto", new StudentDto());
        model.addAttribute("classList", classService.findAll());
        return "students/add";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute StudentDto studentDto, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model) throws DuplicateAdminName {
        // Custom validate thì cần làm:
        new StudentDto().validate(studentDto,bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("classList", classService.findAll());
            return "students/add";
        }

        Student student = new Student();
        // copy thuộc tính của studentDto => student (entiy)
        BeanUtils.copyProperties(studentDto, student);

        studentService.save(student);
        redirectAttributes.addFlashAttribute("mess","add success");
        return "redirect:/students";
    }

    @GetMapping("/detail")
    public String detail1(@RequestParam int id, Model model){
        System.out.println(12/0);
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

    @ExceptionHandler(DuplicateAdminName.class)
    public String handleException(){
        return "admin-duplicate";
    }
}
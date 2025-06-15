package com.codegym.demo_spring_jpa.controller;

import com.codegym.demo_spring_jpa.dto.StudentDto;
import com.codegym.demo_spring_jpa.model.Student;
import com.codegym.demo_spring_jpa.service.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class RestStudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping("")
    public ResponseEntity<List<Student>> getAll() {
        List<Student> studentList = studentService.findAll();
        if (studentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 thanh công nhưng không có trả về giá trị
        }
        return new ResponseEntity<>(studentList, HttpStatus.OK); // 200 : thành công có trả về giá trị
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        Student student = studentService.findById(id.intValue());
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable Long id) {
        Student student = studentService.findById(id.intValue());
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.deleteById(id.intValue());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        Student student = studentService.findById(id.intValue());
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(studentDto, student);
        studentService.save(student);
        return new ResponseEntity<>(student, HttpStatus.NO_CONTENT);
    }
}

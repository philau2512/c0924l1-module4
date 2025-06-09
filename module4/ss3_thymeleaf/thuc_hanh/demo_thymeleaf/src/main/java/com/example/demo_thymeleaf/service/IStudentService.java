package com.example.demo_thymeleaf.service;

import com.example.demo_thymeleaf.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    void add(Student student);
    Student findById(int id);
}
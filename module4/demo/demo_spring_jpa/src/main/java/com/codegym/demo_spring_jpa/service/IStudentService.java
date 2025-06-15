package com.codegym.demo_spring_jpa.service;

import com.codegym.demo_spring_jpa.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    Page<Student> findAll(String searchName, Pageable pageable);
    void save(Student student);
    Student findById(int id);
    void deleteById(int id);
}

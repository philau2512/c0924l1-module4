package com.codegym.demo_spring_jpa.repository;

import com.codegym.demo_spring_jpa.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student, Integer> {
    // tạo câu truy vấn bằng methodname
    Page<Student> findStudentByNameContaining(String searchName, Pageable pageable);
}

package com.codegym.demo_spring_jpa.service;

import com.codegym.demo_spring_jpa.model.Student;
import com.codegym.demo_spring_jpa.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService{
    @Autowired
    private IStudentRepository studenRtepository;

    @Override
    public List<Student> findAll() {
        return studenRtepository.findAll();
    }

    @Override
    public Page<Student> findAll(String searchName, Pageable pageable) {
        return studenRtepository.findStudentByNameContaining(searchName, pageable);
    }

    @Override
    public void save(Student student) {
        studenRtepository.save(student);
    }

    @Override
    public Student findById(int id) {
        return studenRtepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        studenRtepository.deleteById(id);
    }
}

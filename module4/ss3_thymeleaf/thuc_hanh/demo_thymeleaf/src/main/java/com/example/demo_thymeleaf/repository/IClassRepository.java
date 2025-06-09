package com.example.demo_thymeleaf.repository;

import com.example.demo_thymeleaf.model.ClassCG;

import java.util.List;

public interface IClassRepository {
    List<ClassCG> findAll();
    ClassCG findById(int id);
}

package com.example.demo_thymeleaf.service;

import com.example.demo_thymeleaf.model.ClassCG;

import java.util.List;

public interface IClassService {
    List<ClassCG> findAll();
    ClassCG findById(int id);
}
package com.codegym.demo_spring_jpa.service;

import com.codegym.demo_spring_jpa.model.ClassCG;

import java.util.List;

public interface IClassService {
    List<ClassCG> findAll();
    ClassCG findById(int id);
}

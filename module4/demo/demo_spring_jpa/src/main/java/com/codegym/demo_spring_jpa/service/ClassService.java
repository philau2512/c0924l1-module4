package com.codegym.demo_spring_jpa.service;

import com.codegym.demo_spring_jpa.model.ClassCG;
import com.codegym.demo_spring_jpa.repository.IClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService implements IClassService{
    @Autowired
    private IClassRepository classRepository;

    @Override
    public List<ClassCG> findAll() {
        return classRepository.findAll();
    }

    @Override
    public ClassCG findById(int id) {
        return classRepository.findById(id).orElse(null);
    }
}

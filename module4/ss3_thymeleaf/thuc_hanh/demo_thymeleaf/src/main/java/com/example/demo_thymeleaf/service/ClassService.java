package com.example.demo_thymeleaf.service;

import com.example.demo_thymeleaf.model.ClassCG;
import com.example.demo_thymeleaf.repository.IClassRepository;
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
        return classRepository.findById(id);
    }
}
package com.example.demo_thymeleaf.repository;

import com.example.demo_thymeleaf.model.ClassCG;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassRepository implements IClassRepository{
    private static List<ClassCG> classCGList = new ArrayList<>();
    static {
        classCGList.add(new ClassCG(1,"C09"));
        classCGList.add(new ClassCG(2,"C08"));
        classCGList.add(new ClassCG(3,"C07"));
    }

    @Override
    public List<ClassCG> findAll() {
        return classCGList;
    }

    @Override
    public ClassCG findById(int id) {
        for (ClassCG cls: classCGList) {
            if (cls.getId()==id){
                return cls;
            }
        }
        return null;
    }
}

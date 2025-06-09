package com.example.demo_thymeleaf.repository;

import com.example.demo_thymeleaf.model.ClassCG;
import com.example.demo_thymeleaf.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@Repository
public class StudentRepository implements IStudentRepository{
    private static List<Student> studentList = new ArrayList<>();
    static {
        studentList.add(new Student(1,"chánh1",1, Arrays.asList("C++","JAVA","PHP"),new ClassCG(1,"C09")));
        studentList.add(new Student(2,"Hằng",0, Arrays.asList("C++","PHP"),new ClassCG(1,"C09")));
        studentList.add(new Student(3,"ABC",-1, Arrays.asList("C++","PHP","SQL"),new ClassCG(1,"C09")));
    }
    @Override
    public List<Student> findAll() {
        // kết nối DB
        return studentList;
    }

    @Override
    public void add(Student student) {
        studentList.add(student);
    }

    @Override
    public Student findById(int id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId()==id){
                return studentList.get(i);
            }
        }
        return null;
    }
}
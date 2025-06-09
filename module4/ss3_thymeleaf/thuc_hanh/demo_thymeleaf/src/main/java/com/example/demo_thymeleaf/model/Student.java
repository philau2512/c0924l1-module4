package com.example.demo_thymeleaf.model;


import java.util.List;

public class Student {
    private int id;
    private String name;
    private int gender;
    private List<String> languages;
    // lớp học

    private ClassCG classCG;

    public Student() {
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ClassCG getClassCG() {
        return classCG;
    }

    public void setClassCG(ClassCG classCG) {
        this.classCG = classCG;
    }

    public Student(int id, String name, int gender, List<String> languages) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.languages = languages;
    }

    public Student(int id, String name, int gender, List<String> languages, ClassCG classCG) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.languages = languages;
        this.classCG = classCG;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
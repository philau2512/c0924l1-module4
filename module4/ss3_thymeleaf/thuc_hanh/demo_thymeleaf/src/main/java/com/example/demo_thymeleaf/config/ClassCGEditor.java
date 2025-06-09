package com.example.demo_thymeleaf.config;

import com.example.demo_thymeleaf.model.ClassCG;
import com.example.demo_thymeleaf.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class ClassCGEditor extends PropertyEditorSupport {

    @Autowired
    private  IClassService classService;
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println("------ Binding with PropertyEditor: " + text);
        try {
            int id = Integer.parseInt(text);
            ClassCG classCG = classService.findById(id);
            this.setValue(classCG);
        } catch (NumberFormatException e) {
            this.setValue(null);
        }
    }
}
package com.codegym.demo_spring_jpa.dto;

import com.codegym.demo_spring_jpa.model.ClassCG;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto implements Validator {
    private String name;
    private int gender;
    private ClassCG classCG;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudentDto studentDto = (StudentDto) target;
        if ("".equals(studentDto.getName())) {
            errors.rejectValue("name", "notEmpty");
        } else {
            String regex = "^[A-Z][a-z]+(\\s[A-Z][a-z]+)*$";
            if (!studentDto.getName().matches(regex)) {
                errors.rejectValue("name", "notMatch");
            }
        }
    }
}

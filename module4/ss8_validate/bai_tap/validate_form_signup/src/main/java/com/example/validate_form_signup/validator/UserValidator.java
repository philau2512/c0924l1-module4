package com.example.validate_form_signup.validator;

import com.example.validate_form_signup.model.User;
import com.example.validate_form_signup.service.IUserService;
import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

@Component
public class UserValidator implements Validator {
    @Autowired
    private IUserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        // Validate firstName
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            errors.rejectValue("firstName", "firstName.notEmpty", "First Name không được để trống");
        } else if (user.getFirstName().length() < 3 || user.getFirstName().length() > 45) {
            errors.rejectValue("firstName", "firstName.length", "First Name phải có độ dài từ 3 đến 45 ký tự");
        }

        // Validate lastName
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            errors.rejectValue("lastName", "lastName.notEmpty", "Last Name không được để trống");
        } else if (user.getLastName().length() < 2 || user.getLastName().length() > 45) {
            errors.rejectValue("lastName", "lastName.length", "Last Name phải có độ dài từ 2 đến 45 ký tự");
        }

        // Validate password
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            errors.rejectValue("password", "password.notEmpty", "Password không được để trống");
        } else if (user.getPassword().length() < 5 || user.getPassword().length() > 45) {
            errors.rejectValue("password", "password.length", "Password phải có độ dài từ 5 đến 45 ký tự");
        }

        // Validate email
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            errors.rejectValue("email", "email.notEmpty", "Email không được để trống");
        } else if (!user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errors.rejectValue("email", "email.invalid", "Email không hợp lệ");
        } else if (userService.existsByEmail(user.getEmail())) {
            errors.rejectValue("email", "email.exists", "Email đã được sử dụng");
        }

        // Validate phone
        if (user.getPhoneNumber() == null || user.getPhoneNumber().trim().isEmpty()) {
            errors.rejectValue("phoneNumber", "phoneNumber.notEmpty", "Số điện thoại không được để trống");
        } else if (!user.getPhoneNumber().matches("^0[0-9]{9}$")) {
            errors.rejectValue("phoneNumber", "phoneNumber.invalid", "Số điện thoại không hợp lệ (10 chữ số, bắt đầu bằng 0)");
        } else if (userService.existsByPhoneNumber(user.getPhoneNumber())) {
            errors.rejectValue("phoneNumber", "phoneNumber.exists", "Số điện thoại đã được sử dụng");
        }

        // Validate age
        if (user.getAge() < 18) {
            errors.rejectValue("age", "age.min", "Tuổi phải >= 18");
        }
    }
}


package com.codegym.demo_spring_jpa.aop;

import com.codegym.demo_spring_jpa.dto.StudentDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    private static int count = 0;

    @After("execution(* com.codegym.demo_spring_jpa.controller.StudentController.showList(..))")
    public void log() {
        count++;
        System.out.println("------ visited home page number--------------" + count);
    }

//    @AfterReturning("execution(* com.codegym.demo_spring_jpa.controller.StudentController.save(..))")
//    public void loggingAddStudent(JoinPoint joinPoint) {
//        // lấy ra tên student được thêm mới
//        Object[] objects = joinPoint.getArgs();
//        StudentDto studentDtos = (StudentDto) objects[0];
//        System.out.println("------ new student ----" + studentDtos.getName());
//    }
//
//    @AfterThrowing("execution(* com.codegym.demo_spring_jpa.controller.StudentController.save(..))")
//    public void loggingAddStudentThrow(JoinPoint joinPoint) {
//        // lấy ra tên student được thêm mới
//        Object[] objects = joinPoint.getArgs();
//        StudentDto studentDtos = (StudentDto) objects[0];
//        System.out.println("------ Tên trung với ----" + studentDtos.getName());
//    }

    public Object loggingAround(ProceedingJoinPoint joinPoint) {
        System.out.println(" nghiẹp vụ phụ chạy trước nghiệp vụ chính");
        Object object = null;

        try {
            object = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        System.out.println("nghiếp vụ phụ chạy sau nghiệp vụ chính");
        return object;
    }
}

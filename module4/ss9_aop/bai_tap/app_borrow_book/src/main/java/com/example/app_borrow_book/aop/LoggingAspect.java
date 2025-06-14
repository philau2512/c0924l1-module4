package com.example.app_borrow_book.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class LoggingAspect {
    private AtomicInteger visitCount = new AtomicInteger(0);

    @After( "execution(* com.example.app_borrow_book.controller..*(..))")
    public void logVisit() {
        System.out.println("Visit count: " + visitCount.incrementAndGet());
    }

    @AfterReturning("execution(* com.example.app_borrow_book.service.LibraryService.borrowBook(..))")
    public void logBorrow(JoinPoint joinPoint) {
        System.out.println("Book borrowed: Book ID = " + joinPoint.getArgs()[0]);
    }

    @AfterReturning("execution(* com.example.app_borrow_book.service.LibraryService.returnBook(..))")
    public void logReturn(JoinPoint joinPoint) {
        System.out.println("Book returned with code: " + joinPoint.getArgs()[0]);
    }

    @AfterThrowing(pointcut = "execution(* com.example.app_borrow_book.service..*(..))", throwing = "ex")
    public void logError(JoinPoint joinPoint, Throwable ex) {
        System.out.println("Error in method " + joinPoint.getSignature().getName() + ": " + ex.getMessage());
    }
}

package com.codegym.demo_spring_jpa.exception;

public class DuplicateAdminName  extends Exception{
    public DuplicateAdminName(String message) {
        super(message);
    }
}

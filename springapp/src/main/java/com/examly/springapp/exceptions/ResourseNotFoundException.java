package com.examly.springapp.exceptions;

public class ResourseNotFoundException extends RuntimeException {
    public ResourseNotFoundException(String message){
        super(message);
    }
}

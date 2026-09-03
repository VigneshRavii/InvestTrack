package com.examly.springapp.exceptions;

public class PasswordIncorrect extends RuntimeException {
    public PasswordIncorrect(String message) {
        super(message);
    }

}

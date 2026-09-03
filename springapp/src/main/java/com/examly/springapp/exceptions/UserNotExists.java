package com.examly.springapp.exceptions;

public class UserNotExists extends RuntimeException {
    public UserNotExists(String message) {
        super(message);
    }

}

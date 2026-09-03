package com.examly.springapp.exceptions;

public class DuplicateInvestmentException extends RuntimeException {
    public DuplicateInvestmentException(String message){
        super(message);
    }
}

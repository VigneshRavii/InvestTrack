package com.examly.springapp.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateInvestmentException.class)
    public ResponseEntity<String> handleDuplicateInvestmentException(DuplicateInvestmentException ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.valueOf(409));
    }

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<String> handleResourseNotFoundException(ResourseNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.valueOf(404));
    }

    @ExceptionHandler(PasswordIncorrect.class)
    public ResponseEntity<String> handlePasswordIncorrectException(PasswordIncorrect ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(401));
    }

    @ExceptionHandler(UserNotExists.class)
    public ResponseEntity<String> handleUserNotExistsException(UserNotExists ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(404));
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExists ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(409));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

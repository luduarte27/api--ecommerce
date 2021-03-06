package com.ecommerce.ecommerce.handler;

import com.ecommerce.ecommerce.model.error.ErrorMessage;
import com.ecommerce.ecommerce.model.exception.InvalidCpfException;
import com.ecommerce.ecommerce.model.exception.InvalidEmailException;
import com.ecommerce.ecommerce.model.exception.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex){
        ErrorMessage error = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<?> handleInvalidEmailException(InvalidEmailException ex){
        ErrorMessage error = new ErrorMessage("Email Inválido", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCpfException.class)
    public ResponseEntity<?> handleInvalidCpfException(InvalidCpfException ex){
        ErrorMessage error = new ErrorMessage("CPF Inválido", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

package com.taskApp.taskApp.Exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex){
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            errors.append(error.getDefaultMessage()).append("\n");
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
    StringBuilder errors = new StringBuilder();
    ex.getConstraintViolations().forEach(violation -> {
        errors.append(violation.getMessage()).append("\n");
    });
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
}

}

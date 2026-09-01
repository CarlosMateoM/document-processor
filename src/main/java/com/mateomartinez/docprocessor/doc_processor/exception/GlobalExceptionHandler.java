package com.mateomartinez.docprocessor.doc_processor.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.mateomartinez.docprocessor.doc_processor.response.ErrorResponse;
import com.mateomartinez.docprocessor.doc_processor.response.ValidationResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
        ResourceNotFoundException exception,
        WebRequest request
    ) {

        ErrorResponse errorResponse = new ErrorResponse(
            exception.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationResponse> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException exception,
        WebRequest request
    ) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
            .getFieldErrors()
            .forEach(error -> 
                errors.put(error.getField(), error.getDefaultMessage())
            );

        ValidationResponse validationResponse = new ValidationResponse(
            "validation failed",
            errors
        );


        return new ResponseEntity<>(validationResponse, HttpStatus.UNPROCESSABLE_CONTENT);
    }


}

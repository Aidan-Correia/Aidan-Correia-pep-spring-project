package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.entity.*;
import com.example.exception.ClientErrorException;
import com.example.exception.ConflictingResourceException;

@RestControllerAdvice
public class ExceptionController {
    
    @ExceptionHandler(ClientErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity clientErrorHandler(ClientErrorException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid parameters");
    }

    @ExceptionHandler(ConflictingResourceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity conflictErrorHandler(ConflictingResourceException ex)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("conflicting data");
    }
}

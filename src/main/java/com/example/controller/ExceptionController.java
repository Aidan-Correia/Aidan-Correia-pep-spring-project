package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.entity.*;
import com.example.exception.*;

@RestControllerAdvice
public class ExceptionController {
    
    @ExceptionHandler(ClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity clientErrorHandler(ClientException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid parameters");
    }

    @ExceptionHandler(ConflictingResourceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity conflictHandler(ConflictingResourceException ex)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("conflicting data");
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity unauthorizedHandler(UnauthorizedException ex)
    {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity IllegalArgumentHandler(IllegalArgumentException ex)
    {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }



}

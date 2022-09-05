package com.rms.assignment.api.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(value = { EntityNotFoundException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity resourceNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return new ResponseEntity("Entity not found", HttpStatus.NOT_FOUND);
    }
}

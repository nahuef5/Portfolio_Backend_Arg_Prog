package com.portfolio.back.exceptions;

import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GolbalException {
    
    @ExceptionHandler(PersonExistsException.class)
    public ResponseEntity<Mensaje> throwPersonExistsE(PersonExistsException e) {
        return ResponseEntity.badRequest()
                .body(new Mensaje(HttpStatus.NOT_ACCEPTABLE, e.getMessage()));
    }
    @ExceptionHandler(AttributeException.class)
    public ResponseEntity<Mensaje> throwAttributeE(AttributeException e) {
        return ResponseEntity.badRequest()
                .body(new Mensaje(HttpStatus.NOT_ACCEPTABLE, e.getMessage()));
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Mensaje> throwNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje(HttpStatus.NOT_FOUND, e.getMessage()));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Mensaje> badCredentialsException(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje(HttpStatus.NOT_FOUND, "Credenciales erroneas"));
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Mensaje> accessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Mensaje(HttpStatus.FORBIDDEN, "No puede accdeder a este recurso"));
    }
}
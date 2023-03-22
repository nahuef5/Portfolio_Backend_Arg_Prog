package com.portfolio.back.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PersonExistsException extends RuntimeException {

     
    public PersonExistsException(String message) {
        super("Ya existe una instancia de Person en la base de datos");
    }

      
    
}

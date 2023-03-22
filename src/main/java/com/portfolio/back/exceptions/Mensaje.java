package com.portfolio.back.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Mensaje {
    private HttpStatus status;
    private String message;    
}

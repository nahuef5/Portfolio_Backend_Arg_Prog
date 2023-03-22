package com.portfolio.back.email.controller;
import com.portfolio.back.email.model.EmailModel;
import com.portfolio.back.email.service.SendEmailService;
import com.portfolio.back.exceptions.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/send-email/")
@CrossOrigin(origins="http://localhost:8080")
public class EmailController {
    @Autowired
    SendEmailService sendEmailService;
    @PostMapping
    public ResponseEntity<?>sendEmail(@RequestBody EmailModel email) throws Exception{
    sendEmailService.sendEmail(email);
    String msj="Email enviado correctamente.";
    return ResponseEntity.ok(new Mensaje(HttpStatus.OK, msj));
    }
}
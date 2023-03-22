package com.portfolio.back.security.controller;
import com.portfolio.back.exceptions.*;
import com.portfolio.back.security.dto.*;
import com.portfolio.back.security.entity.Usuario;
import com.portfolio.back.security.services.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt/")
@CrossOrigin(origins="http://localhost:8080")
public class AuthController {
    @Autowired
    UserService userService;
    @PostMapping ("create/")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody NewUserDto dto) throws AttributeException, Exception{
        Usuario user = userService.createAdmin(dto);
        return ResponseEntity.ok(new Mensaje(HttpStatus.CREATED,"El administrador "+ user.getUsername() +
        " ha sido creado y sus roles son: " + user.getRoles()));
    }
    @PostMapping ("login/")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginUserDto dto) throws AttributeException{
        TokenDto tokenDto = userService.login(dto);
        return ResponseEntity.ok(tokenDto);
    }
}
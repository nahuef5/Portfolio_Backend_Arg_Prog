package com.portfolio.back.security.services;
import com.portfolio.back.exceptions.*;
import com.portfolio.back.security.dto.*;
import com.portfolio.back.security.entity.*;
import com.portfolio.back.security.jwt.JwtProvider;
import com.portfolio.back.security.repository.UserRepository;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;
    
    public Usuario mapUserFromDto(NewUserDto dto){
        String password= passwordEncoder.encode(dto.getPassword());
        List<RoleEnum>roles=dto.getRoles().stream().map(
                rol->RoleEnum.valueOf(rol)).collect(Collectors.toList());
        return new Usuario(dto.getName(), dto.getSurname(), dto.getUsername(),
                dto.getEmail(), password, roles);
    }
    public Usuario createAdmin(NewUserDto dto) throws AttributeException, Exception{
        if(userRepository.existsByEmail(dto.getEmail()))
            throw new AttributeException("Ya existe un usuario con ese email");
        if(userRepository.existsByUsername(dto.getUsername()))
            throw new AttributeException("Ya existe un usuario con ese email");
        if(!userRepository.findAll().isEmpty()){
            throw new Exception("Solo se puede crear un administrador");
        }
        List<String>roles=Arrays.asList("USER","ADMIN");
        dto.setRoles(roles);
        return userRepository.save(mapUserFromDto(dto));
    }
    public TokenDto login(LoginUserDto loginDto){
        Authentication auth = 
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token= jwtProvider.generateToken(auth);
        return new TokenDto(token);
    }
}
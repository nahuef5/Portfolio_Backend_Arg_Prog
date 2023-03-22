package com.portfolio.back.security.services;
import com.portfolio.back.security.entity.*;
import com.portfolio.back.security.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> userEntity=userRepository.findByUsernameOrEmail(username, username);
        if(!userEntity.isPresent()){
            throw new UsernameNotFoundException("No existe");
        }
        return ImplUserDetails.build(userEntity.get());
    }   
}
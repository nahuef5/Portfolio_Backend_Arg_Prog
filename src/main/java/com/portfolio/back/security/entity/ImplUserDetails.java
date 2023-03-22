package com.portfolio.back.security.entity;

import java.util.Collection;
import java.util.stream.Collectors;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ImplUserDetails implements UserDetails{
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    
    public static ImplUserDetails build(Usuario userEntity){
        Collection<GrantedAuthority>authorities=
                userEntity.getRoles().stream().map(
                rol -> new SimpleGrantedAuthority(rol.name())).collect(Collectors.toList());
        return new ImplUserDetails(userEntity.getName(), userEntity.getSurname(), userEntity.getUsername(), 
        userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
    return password;
    }
    @Override
    public String getUsername() {
    return username;
    }
    @Override
    public boolean isAccountNonExpired() {
    return true;
    }
    @Override
    public boolean isAccountNonLocked() {
    return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
    return true;
    }
    @Override
    public boolean isEnabled() {
    return true;
    }
}
package com.portfolio.back.security.configs;
import com.portfolio.back.security.jwt.*;
import com.portfolio.back.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MainSecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtEntryPoint jwtEntryPoint;
    @Autowired
    JwtFilter jwtFilter;
    AuthenticationManager authenticationManager;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
        AuthenticationManagerBuilder builder=httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
        authenticationManager = builder.build();
        httpSecurity.authenticationManager(authenticationManager);
        httpSecurity.csrf().disable();
        httpSecurity.cors();
        httpSecurity.authorizeRequests().antMatchers("/jwt/**", "/send-email/","/me/read/", "/education/all/","/experience/all/","/skills/all/").permitAll()
                .anyRequest().authenticated();
        httpSecurity.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
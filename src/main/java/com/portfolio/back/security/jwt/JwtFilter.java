package com.portfolio.back.security.jwt;
import com.portfolio.back.security.services.UserDetailsServiceImpl;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter{
    private static final Logger logger=LoggerFactory.getLogger(JwtFilter.class);
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    private String getToken(HttpServletRequest request){
        String header=request.getHeader("Authorization");
        if(header!=null&& header.startsWith("Bearer "))
            return header.replace("Bearer ", "");
        return null;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=getToken(request);
        try{
            if(token !=null && jwtProvider.validateToken(token)){
            String username=jwtProvider.getUsernameFromToken(token);
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authToken = 
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                    null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
            }  
        }catch(UsernameNotFoundException e){
            logger.error("El filtro bloqueo la solicitud");
        }
        filterChain.doFilter(request, response);
    }
}
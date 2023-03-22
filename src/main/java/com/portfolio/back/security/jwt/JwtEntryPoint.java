package com.portfolio.back.security.jwt;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.back.exceptions.Mensaje;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{
    private static final Logger logger= LoggerFactory.getLogger(JwtEntryPoint.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Token no encontrado o invalido");
        Mensaje msj= new Mensaje(HttpStatus.UNAUTHORIZED, "Token no encontrado o invalido");
        response.setContentType("application/json");
        response.setStatus(msj.getStatus().value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(msj));
        response.getWriter().flush();
        response.getWriter().close();
    }
}

package com.portfolio.back.security.jwt;
import com.portfolio.back.security.entity.ImplUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    private static final Logger logger=LoggerFactory.getLogger(JwtProvider.class);
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;
    private Key getKey(String secret){
        byte[]secretByte=Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretByte);
    }
    private List<String> getRoles(ImplUserDetails implUserDetails){
        return implUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
    public String generateToken(Authentication auth){
        ImplUserDetails implUserDetails= (ImplUserDetails) auth.getPrincipal();
        return Jwts.builder()
                .signWith(getKey(secret))
                .setSubject(implUserDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+expiration*1000))
                .claim("roles", getRoles(implUserDetails))
                .claim("x", "d").compact();
    }
    public String getUsernameFromToken(String token){
     return Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJws(token).getBody();
            return true;
        }catch(ExpiredJwtException e){
            logger.error("Expirado Token");
        }catch(UnsupportedJwtException e){
            logger.error("No soportado el Token");
        }catch(MalformedJwtException e){
            logger.error("Mal formado el Token");
        }catch(SignatureException e){
            logger.error("Mala firma");
        }catch(IllegalArgumentException e){
            logger.error("Argumentos ilegales");
        }catch(Exception e){
            logger.error("Fallo el Token");
        }
        return false;  
    }
}
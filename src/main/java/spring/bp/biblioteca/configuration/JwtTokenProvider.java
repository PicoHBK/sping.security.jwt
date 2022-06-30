package spring.bp.biblioteca.configuration;


import java.util.Date;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import spring.bp.biblioteca.BibliotecaApplication;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-miliseconds}")
    private int jwtExpirationMileseconds;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date fechaActual= new Date();
        Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationMileseconds);

        String token = Jwts.builder().setSubject(username)
                .setIssuedAt(fechaActual)
                .setExpiration(fechaExpiracion)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return token;
    }

    public String ObtenerUsernameDelJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();

    }

    public boolean validarToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            return false;
        }
    }
}

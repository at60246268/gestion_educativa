package pe.edu.idat.gestion.app.ms.planillas.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class JwtService
{
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationms;

    //1.- Generar token
    public String generateToken(UserDetails userDetails)
    {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails)
    {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() +expirationms))
                .signWith(getSigninKey())
                .compact();
    }

    public SecretKey getSigninKey()
    {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    //- Extraer datos

    public String extractUsername(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    public <T>  T extractClaim(String token, Function<Claims, T> resolver)
    {
        return resolver.apply(extractAllClaims(token));
    }

    public Claims extractAllClaims(String token)
    {
        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }

    public long getExpirationms()
    {
        return expirationms;
    }

    public boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }

    //- Validar token
    public boolean isTokenValid(String token, UserDetails userDetails)
    {
        try {
            final String username =  extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        }
        catch (Exception e)
        {
            log.warn("Token invalido: {} ", e.getMessage());
            return false;
        }
    }

}

package com.jjoaquin3.forohub.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jjoaquin3.forohub.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService
{
    @Value("${api.security.secret}")
    private String secret;

    public String getToken(User user)
    {
        try
        {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer("Foro Hub")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(getExpiration())
                    .sign(algorithm);
        }
        catch (JWTCreationException exception)
        {
            throw new RuntimeException(exception);
        }
    }

    private Instant getExpiration()
    {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }

    public String getSubject(String token)
    {
        if (token == null || token.isEmpty())
        {
            throw new RuntimeException("Token es nulo o está vacio");
        }

        DecodedJWT verifier;
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(secret); // validando firma
            verifier = JWT
                    .require(algorithm)
                    .withIssuer("Foro Hub")
                    .build()
                    .verify(token);

            if (verifier == null)
            {
                throw new RuntimeException("Verifier es nulo");
            }
            return verifier.getSubject();
        }
        catch (JWTVerificationException exception)
        {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}

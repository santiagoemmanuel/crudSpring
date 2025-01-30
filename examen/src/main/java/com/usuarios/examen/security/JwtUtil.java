package com.usuarios.examen.security;



import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


@Component
public class JwtUtil {

    private static String   SECRET_KEY = "abc123";
    private static Algorithm ALGORITHM =   Algorithm.HMAC256(SECRET_KEY); 

    
    public String create(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer("abcde")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)))
                .sign(ALGORITHM);
    }


    

}

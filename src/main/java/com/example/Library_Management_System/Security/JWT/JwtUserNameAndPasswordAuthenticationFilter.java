package com.example.Library_Management_System.Security.JWT;

import com.example.Library_Management_System.Security.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.*;
import java.util.Date;

public class JwtUserNameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    @Value("${JWT_KEY}")
    private final String JWT_KEY;
    @Value("${JWT_EXPIRATION_DAYS}")
    private final long JWT_EXPIRATION_DAYS;

    public JwtUserNameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager,
                                                      String JWT_KEY,
                                                      long JWT_EXPIRATION_DAYS){
        this.authenticationManager = authenticationManager;
        this.JWT_KEY = JWT_KEY;
        this.JWT_EXPIRATION_DAYS = JWT_EXPIRATION_DAYS;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequest userNameAndPassword = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginRequest.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userNameAndPassword.getUserName(),
                    userNameAndPassword.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(JWT_EXPIRATION_DAYS)))
                .signWith(Keys.hmacShaKeyFor(JWT_KEY.getBytes()))
                .compact();
        response.addHeader("Authorization", "Bearer " + token);
    }


}

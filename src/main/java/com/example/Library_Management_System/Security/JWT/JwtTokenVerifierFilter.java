package com.example.Library_Management_System.Security.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifierFilter extends OncePerRequestFilter {

    private final String JWT_KEY;

    public JwtTokenVerifierFilter(String JWT_KEY){
        this.JWT_KEY = JWT_KEY;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null ||
                authorizationHeader.isEmpty() ||
                !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorizationHeader.replace("Bearer ", "");

        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(JWT_KEY.getBytes()))
                    .parseClaimsJws(token);

            Claims body = claims.getBody();

            String userName = body.getSubject();

            Set<SimpleGrantedAuthority> authorities = ((List<Map<String, String>>)body.get("authorities")).stream()
                    .map(object -> new SimpleGrantedAuthority(object.get("authority"))).collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(userName, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (JwtException e){
            throw new IllegalStateException(String.format("Token %s cannot be truest", token));
        }
        filterChain.doFilter(request, response);
    }
}

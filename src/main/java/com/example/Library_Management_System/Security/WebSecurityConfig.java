package com.example.Library_Management_System.Security;

import com.example.Library_Management_System.Security.JWT.JwtTokenVerifierFilter;
import com.example.Library_Management_System.Security.JWT.JwtUserNameAndPasswordAuthenticationFilter;
import com.example.Library_Management_System.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    private final String JWT_KEY;
    private final long JWT_EXPIRATION_DAYS;

    WebSecurityConfig(UserServiceImpl userDetailsService,
                      PasswordEncoder passwordEncoder,
                      @Value("${JWT_KEY}")String JWT_KEY,
                      @Value("${JWT_EXPIRATION_DAYS}")long JWT_EXPIRATION_DAYS){
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.JWT_KEY = JWT_KEY;
        this.JWT_EXPIRATION_DAYS = JWT_EXPIRATION_DAYS;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationManager(http);

        http
                .csrf((object0) -> {
                    try {
                        object0.disable()
                                .sessionManagement((object1) -> object1.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .authenticationManager(authenticationManager)
                .addFilter(new JwtUserNameAndPasswordAuthenticationFilter(authenticationManager,
                        JWT_KEY,
                        JWT_EXPIRATION_DAYS))
                .addFilterAfter(new JwtTokenVerifierFilter(JWT_KEY), JwtUserNameAndPasswordAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/users").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
        return http.build();
    }

//    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
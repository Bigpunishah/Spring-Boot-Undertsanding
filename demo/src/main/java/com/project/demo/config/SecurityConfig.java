package com.project.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    // Password encrypter
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Authorizing access
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/api/v1/user/newuser").permitAll()
                        .requestMatchers("/api/v1/user/login/authenticate").permitAll()

                        .requestMatchers("/api/v1/user/**").hasAuthority("USER")
                        .requestMatchers("/api/v1/tasks/**").hasAuthority("ADMIN")
                        .requestMatchers("/task").hasAuthority("ADMIN")

                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").permitAll()); // sending to html page

        return http.build();
    }

    // todo This also works
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {

    // http.csrf(csrf -> csrf.disable());
    // http.authorizeHttpRequests(auth -> auth.requestMatchers("/").permitAll());
    // //Public access
    // http.authorizeHttpRequests(auth ->
    // auth.requestMatchers("/api/v1/user/login/authenticate").permitAll());
    // http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
    // .formLogin(form -> form.loginPage("/login").permitAll());
    // return http.build();

    // has...() Takes "String" & .name() returns string
    // hasAnyRole() multiple roles
    // hasRole() one role
    // hasAnyAuthority() multiple authorities allowed.
    // hasAuthority() one authorirty allowed.
    // }

}

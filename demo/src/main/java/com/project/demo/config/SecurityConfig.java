package com.project.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

// import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Password encrypter
    @Bean
    public PasswordEncoder passwordEncoder() {
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

                        .requestMatchers("/api/v1/user/**").hasAnyRole("USER")
                        .requestMatchers("/api/v1/tasks/**").hasAnyRole("ADMIN")
                        .requestMatchers("/task").hasAnyRole("ADMIN")

                        .anyRequest().authenticated())
                        .httpBasic(withDefaults())
                        ;

                // .formLogin(form -> form.loginPage("/login").permitAll()); // sending to html page

        return http.build();
    }

    
}

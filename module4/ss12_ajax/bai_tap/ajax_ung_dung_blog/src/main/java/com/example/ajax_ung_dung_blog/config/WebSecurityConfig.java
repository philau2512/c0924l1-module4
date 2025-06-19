package com.example.ajax_ung_dung_blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Public access to login, static resources, and view endpoints
                        .requestMatchers(HttpMethod.GET, "/", "/login", "/blogs", "/blogs/view/**", "/categories", "/api/v1/blogs", "/api/v1/blogs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        // Require authentication for creating, editing, deleting (MVC endpoints)
                        .requestMatchers("/blogs/create", "/blogs/edit/**", "/blogs/delete/**", "/categories/create", "/categories/edit/**", "/categories/delete/**").authenticated()
                        // Secure REST API endpoints
                        .requestMatchers(HttpMethod.POST, "/api/v1/blogs").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/blogs/**").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/blogs/**").hasRole("USER")
                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/blogs", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/blogs")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // Disable CSRF for demo

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(passwordEncoder.encode("12345"))
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build());
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
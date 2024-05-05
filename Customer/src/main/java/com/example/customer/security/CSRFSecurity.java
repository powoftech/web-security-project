package com.example.customer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class CSRFSecurity {
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/cart", "/add-to-cart", "/update-cart", "/check-out", "/add-order", "/customer-profile",
                        "/update-profile", "/change-password",
                        "/home", "/", "/login", "/do-login", "/register", "/do-register", "/logout", "/order",
                        "/cancel-order", "/order-view/**",
                        "/product", "/product-detail/**", "/search-products", "/search-product/**", "/order-product/**")
                .permitAll()
                .antMatchers("/blog/**", "/css/**", "/fonts/**", "/image/**", "/images/**", "/js/**", "/vendor/**",
                        "/main.js", "/style.css", "/customJs.js")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        return http.build();
    }
}

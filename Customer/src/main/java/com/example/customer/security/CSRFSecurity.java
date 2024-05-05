package com.example.customer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CSRFSecurity {

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .antMatchers("/css/**", "/js/**", "/img/**", "/vendor/**", "/scss/**", "/customCss/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf();
        return http.build();
    }
}

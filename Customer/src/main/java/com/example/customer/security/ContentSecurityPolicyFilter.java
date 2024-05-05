package com.example.customer.security;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ContentSecurityPolicyFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // Pass nonce values to Thymeleaf template
        httpResponse.setHeader("Content-Security-Policy", "default-src 'self'; " +
                "frame-ancestors 'none'; " +
                "form-action 'self';" +
                "style-src 'self' https://cdn.jsdelivr.net;" + // Include nonce values in style-src
                "script-src 'self' https://cdn.jsdelivr.net;" + // Added semicolon and 'unsafe-inline'
                "img-src 'self' data:;" + // Added semicolon
                "font-src 'self' https://cdn.linearicons.com;");
        chain.doFilter(request, response);
    }
}

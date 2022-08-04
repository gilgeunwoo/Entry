package com.example.entry.global.error;


import com.example.entry.global.exception.CustomException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(request, response);
        } catch (CustomException e) {
            CustomErrorResponse errorResponse = new CustomErrorResponse(e.getErrorCode().getMessage());

            response.setStatus(e.getErrorCode().getStatus());
            response.setContentType("application/json");
            response.getWriter().write(errorResponse.convertToJson(errorResponse));
        }
    }
}

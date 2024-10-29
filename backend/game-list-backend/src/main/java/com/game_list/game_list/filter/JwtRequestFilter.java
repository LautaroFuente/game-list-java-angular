package com.game_list.game_list.filter;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.game_list.game_list.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component // Permite que Spring gestione este filtro como un bean
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization"); // Obtener el encabezado Authorization

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Extraer el token del encabezado
            username = jwtService.extractUsername(jwt); // Extraer el nombre de usuario del token
        }
        if (username != null && jwt != null && jwtService.validateToken(jwt, username)) {
        	chain.doFilter(request, response);
        }
        else {
        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }
    }
}


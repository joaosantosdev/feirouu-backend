package com.joaosantosdev.feirouu.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAutorizacaoFilter extends BasicAuthenticationFilter {
    private JWTUtil jwtUtil;
   private UserDetailsService userDetailsService;

    public JWTAutorizacaoFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorization = request.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer ")){
            UsernamePasswordAuthenticationToken auth = this.getAuthentication(authorization.substring(7));

            if(auth != null){
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token){
        if (this.jwtUtil.validateToken(token)) {
            String email = this.jwtUtil.getEmail(token);
            UserDetails user = this.userDetailsService.loadUserByUsername(email);
            return new UsernamePasswordAuthenticationToken(user, null , user.getAuthorities());
        }
        return null;
    }
}

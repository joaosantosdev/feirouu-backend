package com.joaosantosdev.feirouu.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaosantosdev.feirouu.adapters.in.web.dtos.AutenticacaoDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAutenticacaoFilter extends UsernamePasswordAuthenticationFilter implements AuthenticationFailureHandler {
    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    public JWTAutenticacaoFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil){
        this.setAuthenticationFailureHandler(this);
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{

            AutenticacaoDTO autenticacaoDTO = new ObjectMapper().readValue(request.getInputStream(), AutenticacaoDTO.class);
            UsernamePasswordAuthenticationToken autToken = new UsernamePasswordAuthenticationToken(autenticacaoDTO.getEmail(),
                    autenticacaoDTO.getSenha(),
                    new ArrayList<>());
            return this.authenticationManager.authenticate(autToken);
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        UsuarioDetails user = (UsuarioDetails) auth.getPrincipal();

        String token = this.jwtUtil.generateToken(user.getUsername());
        response.addHeader("access-control-expose-headers", "Authorization");
        response.addHeader("Authorization", "Bearer " + token);
    }


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().append(json());

    }

    private String json() {
        long date = new Date().getTime();
        return "{\"timestamp\": " + date + ", " + "\"status\": 401, " + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", " + "\"path\": \"/login\"}";
    }

}

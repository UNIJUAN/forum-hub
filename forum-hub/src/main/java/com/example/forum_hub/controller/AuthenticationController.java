package com.example.forum_hub.controller;

import com.example.forum_hub.domain.user.User;
import com.example.forum_hub.dto.user.AuthenticationDTO;
import com.example.forum_hub.dto.user.TokenResponseDTO;
import com.example.forum_hub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> authenticate(@RequestBody @Valid AuthenticationDTO data) {
        try {
            System.out.println("Intentando autenticar usuario: " + data.login());
            var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());

            // Intento de autenticación
            var authentication = authenticationManager.authenticate(authenticationToken);
            System.out.println("Autenticación exitosa");

            // Generación del token
            var token = tokenService.generateToken((User) authentication.getPrincipal());
            System.out.println("Token generado correctamente");

            return ResponseEntity.ok(new TokenResponseDTO(token));
        } catch (Exception e) {
            System.out.println("Error en autenticación: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
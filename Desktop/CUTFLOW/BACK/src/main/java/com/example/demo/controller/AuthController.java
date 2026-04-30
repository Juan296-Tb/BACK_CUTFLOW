package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.security.JwtService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {

        String username = request.get("username");

        if (username == null || username.isEmpty()) {
            throw new RuntimeException("Username requerido");
        }

        String token = jwtService.generarToken(username);

        return Map.of("token", token);
    }
}
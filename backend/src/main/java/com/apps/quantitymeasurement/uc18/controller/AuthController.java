package com.apps.quantitymeasurement.uc18.controller;

import com.apps.quantitymeasurement.uc18.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @GetMapping("/login-success")
    public String loginSuccess(

            Authentication authentication
    ) {

        String email =
                authentication.getName();

        String token =
                jwtService.generateToken(email);

        return token;
    }
}

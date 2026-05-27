package com.apps.quantitymeasurement.uc18.controller;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")

    private String clientId;

    @GetMapping("/test")
    public String test() {

        return clientId;
    }
}
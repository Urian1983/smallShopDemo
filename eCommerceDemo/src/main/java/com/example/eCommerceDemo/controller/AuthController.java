package com.example.eCommerceDemo.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    /*
    Endpoints típicos:

POST /auth/login → login con email/password, devuelve JWT

POST /auth/register → crea un usuario nuevo, asigna rol predeterminado

POST /auth/refresh → refresco de token (opcional)

POST /auth/logout → opcional
     */
}

package com.sabor.gourmet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    // Renderiza templates/login.html
    @GetMapping("/login")
    public String loginView() {
        return "login";
    }
}

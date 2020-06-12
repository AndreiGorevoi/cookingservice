package com.gorevoi.cookingservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String getLoginForm(){
        return "login";
    }

    @GetMapping(value = "/registration")
    public String getRegistration(){
        return "registration";
    }
}

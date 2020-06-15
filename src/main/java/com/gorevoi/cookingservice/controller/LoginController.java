package com.gorevoi.cookingservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String getLoginForm(Model model,
            @RequestParam(required = false) String error){
        if(error==null){
            return "login";
        }
        model.addAttribute("error","error");
        return "login";
    }

    @GetMapping(value = "/registration")
    public String getRegistration(){
        return "registration";
    }
}

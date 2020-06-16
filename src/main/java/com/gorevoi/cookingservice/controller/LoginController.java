package com.gorevoi.cookingservice.controller;

import com.gorevoi.cookingservice.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/")
    public String mainPage(HttpServletRequest request) {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.getSession().setAttribute("userId", user.getId());
        return "index";
    }
}

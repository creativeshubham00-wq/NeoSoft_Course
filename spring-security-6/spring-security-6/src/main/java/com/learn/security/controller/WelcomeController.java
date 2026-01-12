package com.learn.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    //1.For testing purpose
    @GetMapping
    public String welcome(){
        return "welcome to neosoft";
    }

    //2.This will give us CRSF token
    @GetMapping("/csrf")
    public CsrfToken getToken(HttpServletRequest request){
     return (CsrfToken) request.getAttribute("_crsf");
    }
}

package com.farmacia.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}

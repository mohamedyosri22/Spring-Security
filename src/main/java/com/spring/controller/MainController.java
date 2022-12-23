package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class MainController {
    @GetMapping("/main")
    public String index(){
        return "index";
    }

    @GetMapping("/profile")
    public String fr(){
        return "profile/index";
    }
}

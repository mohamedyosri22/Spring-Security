package com.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:9090/

@RestController
@RequestMapping("api/basic") // http://localhost:9090/api/basic
public class BasicController {

    // http://localhost:9090/api/basic/mybasic
    @GetMapping("/mybasic")
    public String login(){
        return "My name is mohamed yosri";
    }



    // http://localhost:9090/api/basic/allbasic
    @GetMapping("/allbasic")
    public String all(){
        return "suck my dick";
    }
}

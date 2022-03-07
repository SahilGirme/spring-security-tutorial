package com.coderspark.client.controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public  String hello(){
        return "Hello, Welcome to Coders Park!!";
    }
}

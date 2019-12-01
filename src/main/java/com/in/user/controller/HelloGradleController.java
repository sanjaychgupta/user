package com.in.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HelloGradleController {

    @GetMapping(path="hello")
    public String helloGradle() {
        return "Hello Gradle!";
    }

}
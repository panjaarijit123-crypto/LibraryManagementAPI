package com.library.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/api/")
public class HomeController {

    @GetMapping(value="/")
    public String home() {
        return "Welcome to the Library Management API!";
    }
}
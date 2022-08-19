package com.onlinestoreapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonController {

    @GetMapping("/signup")
    public String authPage(Model model) {
        return "signup";
    }
}

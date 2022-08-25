package com.onlinestoreapp.controllers;

import com.onlinestoreapp.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "/auth/signup";
    }
}

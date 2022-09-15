package com.onlinestoreapp.controllers;

import com.onlinestoreapp.models.Person;
import com.onlinestoreapp.security.PersonDetails;
import com.onlinestoreapp.services.PersonService;
import com.onlinestoreapp.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainPageController {

    private final PersonService personService;
    private final ProductsService productsService;

    @Autowired
    public MainPageController(PersonService personService, ProductsService productsService) {
        this.personService = personService;
        this.productsService = productsService;
    }

    @GetMapping()
    public String index(Model model) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal().toString();

        System.out.println("\n\n\n\n===>>>   " + username);
//        model.addAttribute("person", pd);
        return "content/index";
    }

    @GetMapping("/admin/admin-panel")
    public String gotoAdminPanelPage(Model model) {
        model.addAttribute("allPersons", personService.getAll());
        model.addAttribute("allProducts", productsService.getAll());
        return "admin-control/admin-panel";
    }

    @GetMapping("/cart/{id}")
    public String gotoPersonsCartPage(@PathVariable("id") Integer cartId) {
        return "";
    }
}

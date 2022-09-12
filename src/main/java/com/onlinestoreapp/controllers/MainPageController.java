package com.onlinestoreapp.controllers;

import com.onlinestoreapp.services.PersonService;
import com.onlinestoreapp.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String index() {
        return "content/index";
    }

    @GetMapping("/admin/admin-panel")
    public String gotoAdminPanelPage(Model model) {
        model.addAttribute("allPersons", personService.getAll());
        model.addAttribute("allProducts", productsService.getAll());
        return "admin-control/admin-panel";
    }
}

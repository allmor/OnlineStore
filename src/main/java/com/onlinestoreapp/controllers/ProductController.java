package com.onlinestoreapp.controllers;

import com.onlinestoreapp.models.Product;
import com.onlinestoreapp.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ProductController {

    private final ProductsService productsService;

    @Autowired
    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping()
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/add-product")
    public String newItemForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @GetMapping("/show-product/{id}")
    public String showItem(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("product", productsService.getItemById(id));

        return "show-product";
    }

    @PostMapping("/add-product")
    public String addItem(@ModelAttribute("product") Product product,
                          @RequestParam MultipartFile file1,
                          @RequestParam MultipartFile file2,
                          @RequestParam MultipartFile file3) throws IOException {

        productsService.saveItem(product, file1, file2, file3);
        return "redirect:/";
    }
}

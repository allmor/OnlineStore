package com.onlinestoreapp.controllers;

import com.onlinestoreapp.repositories.ImagesRepository;
import com.onlinestoreapp.repositories.ProductCategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProductCategoryController {
    private final ProductCategoryRepository productCategoryRepository;
    private final ImagesRepository imagesRepository;
    public ProductCategoryController(ProductCategoryRepository productCategoryRepository, ImagesRepository imagesRepository) {
        this.productCategoryRepository = productCategoryRepository;
        this.imagesRepository = imagesRepository;
    }

    @GetMapping("/test-test")
    public String showImage(Model model) {
        Path path = Paths.get("C://testimage//home_appliance.jpeg");
        System.out.println(path);
        model.addAttribute("path", imagesRepository.getFirstById(1));
        return "testtest";
    }
}

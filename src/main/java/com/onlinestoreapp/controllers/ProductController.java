package com.onlinestoreapp.controllers;

import com.onlinestoreapp.models.Product;
import com.onlinestoreapp.services.ProductTypesService;
import com.onlinestoreapp.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductsService productsService;
    private final ProductTypesService productTypesService;

    @Autowired
    public ProductController(ProductsService productsService, ProductTypesService productTypesService) {
        this.productsService = productsService;
        this.productTypesService = productTypesService;
    }


    @GetMapping("/admin/add-product")
    public String newItemForm(Model model) {
        model.addAttribute("newProduct", new Product());
        model.addAttribute("allProductTypes", productTypesService.getAllTypes());
        return "admin-control/product-control/add-product";
    }

    @PostMapping("/admin/add-product")
    public String addItem(@ModelAttribute("newProduct") Product product,
                          @RequestParam("file1") MultipartFile file1,
                          @RequestParam("file2") MultipartFile file2,
                          @RequestParam("file3") MultipartFile file3) throws IOException {
        List<MultipartFile> list = new ArrayList<>();
        if (!file1.isEmpty()) list.add(file1);
        if (!file2.isEmpty()) list.add(file2);
        if (!file3.isEmpty()) list.add(file3);


        productsService.saveItem(product, list);
        return "redirect:/";
    }

    @GetMapping("/show-product/{id}")
    public String showItem(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("product", productsService.getItemById(id));

        return "content/show-product";
    }


}

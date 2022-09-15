package com.onlinestoreapp.controllers;

import com.onlinestoreapp.models.Product;
import com.onlinestoreapp.models.ProductCategory;
import com.onlinestoreapp.models.ProductType;
import com.onlinestoreapp.repositories.ProductCategoryRepository;
import com.onlinestoreapp.services.ProductTypesService;
import com.onlinestoreapp.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    private final ProductsService productsService;
    private final ProductTypesService productTypesService;
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public CatalogController(ProductsService productsService, ProductTypesService productTypesService, ProductCategoryRepository productCategoryRepository) {
        this.productsService = productsService;
        this.productTypesService = productTypesService;
        this.productCategoryRepository = productCategoryRepository;
    }

    @GetMapping()
    public String showCatalog() {
        return "content/catalog";
    }

    @GetMapping("category/{categoryType}")
    public String showCategorySubCatalog(@PathVariable("categoryType") String categoryType,
                                         Model model) {
        ProductCategory productCategory = productCategoryRepository.findByCategoryName(categoryType);
        List<ProductType> productTypesList = productCategory.getProductTypes();

        model.addAttribute("productCategoryCorrectlyName", getCorrectlyName(productCategory.getCategoryName()));
        model.addAttribute("product_type_list", productTypesList);
        return "content/product_category";
    }

//    @GetMapping("subcategory/{subCategoryType}")
//    public String showProductCatalog(Model model,
//                                         @ModelAttribute("subCategoryType") String subType) {
//        ProductCategory productCategory = productTypesService.getProductCategoryFromProductType(subType);
//        List<Product> productList = productsService.getProductsByType(subType);
//        model.addAttribute("products", productList);
//        model.addAttribute("product_category_name", productCategory.getCategoryName());
//        model.addAttribute("productCategoryCorrectlyName", getCorrectlyName(productCategory.getCategoryName()));
//        String subTypeForName = subType.substring(0, 1).toUpperCase(Locale.ROOT) + subType.substring(1);
//
//        model.addAttribute("productTypeName", subTypeForName);
//        return "content/products_list";
//    }

    @GetMapping("subcategory/{subCategoryType}")
    public String showProductList(Model model, @ModelAttribute("subCategoryType") String subType) {
        return showProductListWithPage(model, subType, 1);
    }

    // WITH PAGINATION
    @GetMapping("subcategory/{subCategoryType}/{pageNumber}")
    public String showProductListWithPage(Model model,
                                          @ModelAttribute("subCategoryType") String subType,
                                          @PathVariable("pageNumber") Integer currentPage) {
        Page<Product> page = productsService.findProductPage(subType, currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Product> productList = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("typeName", subType);
        ProductCategory productCategory = productTypesService.getProductCategoryFromProductType(subType);
        //List<Product> productList = productsService.getProductsByType(subType);
        model.addAttribute("products", productList);
        model.addAttribute("product_category_name", productCategory.getCategoryName());
        model.addAttribute("productCategoryCorrectlyName", getCorrectlyName(productCategory.getCategoryName()));
        String subTypeForName = subType.substring(0, 1).toUpperCase(Locale.ROOT) + subType.substring(1);

        model.addAttribute("productTypeName", subTypeForName);
        return "content/products_list_with_pages";
    }

    // METHOD TO TRANSFORM TO NORMAL NAME
    private String getCorrectlyName(String temp) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(temp.split("_"))
                .map(s -> s.substring(0, 1).toUpperCase(Locale.ROOT).concat(s.substring(1).toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList()).forEach(s -> sb.append(s + " "));
        return sb.toString();
    }
}

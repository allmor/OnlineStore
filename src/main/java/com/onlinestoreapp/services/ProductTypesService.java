package com.onlinestoreapp.services;

import com.onlinestoreapp.models.ProductCategory;
import com.onlinestoreapp.models.ProductType;
import com.onlinestoreapp.repositories.ProductCategoryRepository;
import com.onlinestoreapp.repositories.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypesService {

    private final ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductTypesService(ProductTypeRepository productTypeRepository, ProductCategoryRepository productCategoryRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public List<ProductType> getAllTypes() {
        return productTypeRepository.findAll();
    }

    public ProductType getProductType(String productTypeName) {
        return productTypeRepository.findProductTypeByProductTypeName(productTypeName);
    }

    public ProductCategory getProductCategoryFromProductType(String productTypeName) {
        return getProductType(productTypeName).getProductCategory();
    }
}

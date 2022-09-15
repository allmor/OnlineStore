package com.onlinestoreapp.repositories;

import com.onlinestoreapp.models.Product;
import com.onlinestoreapp.models.ProductCategory;
import com.onlinestoreapp.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {

    ProductType findProductTypeByProductTypeName(String name);
}

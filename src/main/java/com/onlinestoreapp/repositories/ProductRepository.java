package com.onlinestoreapp.repositories;

import com.onlinestoreapp.models.Product;
import com.onlinestoreapp.models.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findAllByProductType(Pageable pageable, ProductType productType);


//    @Query("""
//            SELECT new com.onlinestoreapp.models.dtos.product.ProductDto(
//            p.productId, p.productName, p.description, p.price,
//            p.amount, p.images, p.productType, p.cart
//            )
//            FROM Product p
//            """)
//    List<ProductDto> findAllDto();
}

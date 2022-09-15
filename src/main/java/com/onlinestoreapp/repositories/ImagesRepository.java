package com.onlinestoreapp.repositories;

import com.onlinestoreapp.models.Image;
import com.onlinestoreapp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<Image, Integer> {
//    List<Image> findByProduct(Product product);
    Image getFirstById(Integer id);
}

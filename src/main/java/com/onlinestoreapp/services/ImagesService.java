package com.onlinestoreapp.services;

import com.onlinestoreapp.models.Image;
import com.onlinestoreapp.models.Product;
import com.onlinestoreapp.repositories.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ImagesService {
    private final ImagesRepository imagesRepository;

    @Autowired
    public ImagesService(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

//    public List<ResponseEntity<?>> getImageByProduct(Product product) {
//        List<Image> images = imagesRepository.findByProduct(product);
//
//        if (images.size() == 0) return null;
//
//        List<ResponseEntity<?>> responseEntities = new ArrayList<>();
//
//        for (Image image : images) {
//            System.out.println("\n\n\n\n");
//            System.out.println("FROM IMAGESERVICE: \n" + image);
//            responseEntities.add(ResponseEntity.ok()
//                    .header("fileName", image.getFileName())
//                    .contentType(MediaType.valueOf(image.getContentType()))
//                    .contentLength(image.getSize())
//                    .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes()))));
//        }
//        return responseEntities;
//    }
}

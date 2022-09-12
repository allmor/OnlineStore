package com.onlinestoreapp.controllers;

import com.onlinestoreapp.models.Image;
import com.onlinestoreapp.repositories.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class ImageController {

    private final ImagesRepository imagesRepository;

    @Autowired
    public ImageController(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<?> getImageById(@PathVariable("id") Integer id) {
        Image image = imagesRepository.findById(id).orElse(null);
        System.out.println("\n\n\n" + image + "\n\n\n");
        return ResponseEntity.ok()
                .header("fileName", image.getFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}

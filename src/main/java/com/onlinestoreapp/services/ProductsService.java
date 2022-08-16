package com.onlinestoreapp.services;

import com.onlinestoreapp.models.Image;
import com.onlinestoreapp.models.Product;
import com.onlinestoreapp.repositories.ImagesRepository;
import com.onlinestoreapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductsService {

    private final ProductRepository productRepository;
    private final ImagesRepository imagesRepository;

    @Autowired
    public ProductsService(ProductRepository productRepository, ImagesRepository imagesRepository) {
        this.productRepository = productRepository;
        this.imagesRepository = imagesRepository;
    }

    public List<Product> getAllItems() {
        return productRepository.findAll();
    }

    public Product getItemById(Integer id) {
        Optional<Product> item = productRepository.findById(id);
        return item.orElse(null);
    }

    // Saving new product with photos
    @Transactional
    public void saveItem(Product product, MultipartFile f1, MultipartFile f2, MultipartFile f3) throws IOException {
        Image image1;
        Image image2;
        Image image3;

        if (f1.getSize() != 0) {
            image1 = toImageConverter(f1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);

            imagesRepository.save(image1);
        }
        if (f2.getSize() != 0) {
            image2 = toImageConverter(f2);
            image2.setPreviewImage(true);
            product.addImageToProduct(image2);

            imagesRepository.save(image2);
        }
        if (f3.getSize() != 0) {
            image3 = toImageConverter(f3);
            image3.setPreviewImage(true);
            product.addImageToProduct(image3);

            imagesRepository.save(image3);
        }


        //setting preview and saving
        Product i = productRepository.save(product);
        i.setPreviewImageId(i.getImages().get(0).getId());
        productRepository.save(product);
    }

    //Multipart file to Image Converter
    private Image toImageConverter(MultipartFile file) throws IOException {
        Image img = new Image();
        img.setFileName(file.getName());
        img.setSize(file.getSize());
        img.setContentType(file.getContentType());
        img.setBytes(file.getBytes());
        return img;
    }
}

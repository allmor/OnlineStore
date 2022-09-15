package com.onlinestoreapp.services;

import com.onlinestoreapp.models.Image;
import com.onlinestoreapp.models.Product;
import com.onlinestoreapp.repositories.ImagesRepository;
import com.onlinestoreapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductsService {

    private final ProductRepository productRepository;
    private final ProductTypesService productTypesService;
    private final ImagesRepository imagesRepository;

    @Autowired
    public ProductsService(ProductRepository productRepository, ProductTypesService productTypesService, ImagesRepository imagesRepository) {
        this.productRepository = productRepository;
        this.productTypesService = productTypesService;
        this.imagesRepository = imagesRepository;

    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getItemById(Integer id) {
        Optional<Product> item = productRepository.findById(id);
        return item.orElse(null);
    }

    // get all products by type
    public List<Product> getProductsByType(String typeName) {
        return productTypesService.getProductType(typeName).getProducts();
    }

    // get all products by type and with pagination
    public Page<Product> findProductPage(String typeName, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 3);
        return productRepository.findAllByProductType(pageable, productTypesService.getProductType(typeName));
    }

    // Saving new product with photos
    @Transactional
    public void saveItem(Product product, List<MultipartFile> list) throws IOException {
        Product temp = null;

        if (list.size() != 0) {

            List<Image> imageList = new ArrayList<>();

            for (MultipartFile mpf : list) {
                if (!mpf.isEmpty()) {
                    Image tempImage = toImageConverter(mpf);
                    tempImage.setPreviewImage(false);
                    imageList.add(tempImage);

                    int indexOfTempImage = imageList.indexOf(tempImage);

                    if (indexOfTempImage == 0) {
                        imageList.get(0).setPreviewImage(true);
                    }

                    product.addImageToProduct(imageList.get(indexOfTempImage));
                    imagesRepository.save(imageList.get(indexOfTempImage));
                }
            }

            temp = productRepository.save(product);
        }

        if (temp != null) {
            temp.setPreviewImageId(product.getImages().get(0).getId());

        }

        productRepository.save(product);



        //setting preview and saving
//        Product productToSave = productRepository.save(product);
//        productToSave.setPreviewImageId(productToSave.getImages().get(0).getId());
//        productRepository.save(product);
    }

    //  Multipart file to Image Converter
    private Image toImageConverter(MultipartFile file) throws IOException {
        Image img = new Image();
        img.setFileName(file.getName());
        img.setSize(file.getSize());
        img.setContentType(file.getContentType());
        img.setBytes(file.getBytes());
        return img;
    }
}

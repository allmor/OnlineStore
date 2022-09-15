package com.onlinestoreapp.models.dtos.product;

import com.onlinestoreapp.models.Cart;
import com.onlinestoreapp.models.Image;
import com.onlinestoreapp.models.ProductType;

import java.time.LocalDateTime;
import java.util.List;

public class ProductDto {

    private Integer productId;

    private String productName;

    private String description;

    private Double price;

    private Integer amount;

    private List<Image> images;

    private ProductType productType;

    private Cart cart;

    private Integer previewImageId;

    private LocalDateTime dateOfCreation;

    public ProductDto(Integer productId, String productName, String description,
                      Double price, Integer amount, List<Image> images,
                      ProductType productType, Cart cart) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.images = images;
        this.productType = productType;
        this.cart = cart;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Integer getPreviewImageId() {
        return previewImageId;
    }

    public void setPreviewImageId(Integer previewImageId) {
        this.previewImageId = previewImageId;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}

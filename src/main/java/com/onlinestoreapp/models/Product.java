package com.onlinestoreapp.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "amount")
    private Integer amount;

    @OneToMany(mappedBy = "product")
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "product_type_id", referencedColumnName = "product_type_id")
    private ProductType productType;

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts;

    @Transient
    private Integer previewImageId;

    @Transient
    private LocalDateTime dateOfCreation;

    @PrePersist
    private void init() {
        dateOfCreation = LocalDateTime.now();
    }

    public Product() {
    }

    public Product(String productName, String description,
                   Double price, Integer amount,
                   List<Image> images, ProductType productType,
                   Integer previewImageId, LocalDateTime dateOfCreation) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.images = images;
        this.productType = productType;
        this.previewImageId = previewImageId;
        this.dateOfCreation = dateOfCreation;
    }

    public Integer getId() {
        return productId;
    }

    public void setId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String title) {
        this.productName = title;
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void addImageToProduct(Image image) {
        if (images == null) images = new ArrayList<>();
        image.setProduct(this);
        images.add(image);
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + productId +
                ", title='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}


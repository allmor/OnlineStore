package com.onlinestoreapp.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "amount")
    private Integer amount;

    @OneToMany(mappedBy = "product")
    private List<Image> images;

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

    public Product(String itemName, String description, Double price) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String title) {
        this.itemName = title;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}


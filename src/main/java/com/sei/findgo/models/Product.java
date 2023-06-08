package com.sei.findgo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productName;

    @Column
    private String description;

    @Column
    private String category;

    @Column
    private double price;

    @Column
    private String productImg;

    @Column
    private int quantity;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "storeSection_id")
    private StoreSection storeSection;

    public Product() {
    }

    public Product(String productName, String description, String category, double price, String productImg, int quantity) {
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.price = price;
        this.productImg = productImg;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public StoreSection getStoreSection() {
        return storeSection;
    }

    public void setStoreSection(StoreSection storeSection) {
        this.storeSection = storeSection;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", productImg='" + productImg + '\'' +
                ", quantity=" + quantity +
                ", storeSection=" + storeSection +
                '}';
    }
}

package com.example.ecommerce_example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private int quantity;

    private String name;

    private double price;

    private String imageName;

    // Default constructor
    public OrderItem() {
    }

    // Parameterized constructor (optional)
    public OrderItem(Order order, Product product, int quantity, String name, double price, String imageName) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.imageName = imageName;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    // toString() method

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + (order != null ? order.getId() : null) +
                ", product=" + (product != null ? product.getId() : null) +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}


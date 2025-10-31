package com.example.ecommerce_example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // "order" is a reserved keyword in some databases, so use "orders"
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private LocalDateTime orderDate;

    private double totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    // Default constructor
    public Order() {
    }

    // Parameterized constructor (optional)
    public Order(String userEmail, LocalDateTime orderDate, double totalAmount) {
        this.userEmail = userEmail;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    // Add an item to the order
    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }

    // Remove an item from the order
    public void removeItem(OrderItem item) {
        items.remove(item);
        item.setOrder(null);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userEmail='" + userEmail + '\'' +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", items=" + items.size() +
                '}';
    }
}

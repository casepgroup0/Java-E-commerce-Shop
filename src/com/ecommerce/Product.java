package com.ecommerce;

public class Product {
    private int productID;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(int productID, String name, double price, int stockQuantity) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative.");
        if (stockQuantity < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters
    public int getProductID() { return productID; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }

    // Setters
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative.");
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        this.stockQuantity = stockQuantity;
    }

    public boolean isAvailable() {
        return stockQuantity > 0;
    }

    @Override
    public String toString() {
        return String.format("[ID: %d] %-20s $%.2f  (Stock: %d)", productID, name, price, stockQuantity);
    }
}

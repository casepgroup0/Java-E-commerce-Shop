package com.ecommerce.orders;

import com.ecommerce.Customer;
import com.ecommerce.Product;
import java.util.List;

public class Order {
    // Possible order statuses
    public enum Status { PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED }

    private int orderID;
    private Customer customer;
    private List<Product> products;
    private double orderTotal;
    private Status status;

    public Order(int orderID, Customer customer, List<Product> products) {
        if (customer == null) throw new IllegalArgumentException("Customer cannot be null.");
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one product.");
        }
        this.orderID = orderID;
        this.customer = customer;
        this.products = products;
        this.orderTotal = products.stream().mapToDouble(Product::getPrice).sum();
        this.status = Status.PENDING;
    }

    // Getters
    public int getOrderID() { return orderID; }
    public Customer getCustomer() { return customer; }
    public List<Product> getProducts() { return products; }
    public double getOrderTotal() { return orderTotal; }
    public Status getStatus() { return status; }

    // Update order status
    public void updateStatus(Status newStatus) {
        if (this.status == Status.CANCELLED) {
            System.out.println("  Cannot update a cancelled order.");
            return;
        }
        this.status = newStatus;
        System.out.println("  Order #" + orderID + " status updated to: " + newStatus);
    }

    // Print a neat order summary
    public void printOrderSummary() {
        System.out.println("\n  ========== Order Summary ==========");
        System.out.println("  Order ID   : #" + orderID);
        System.out.println("  Customer   : " + customer.getName() + " (ID: " + customer.getCustomerID() + ")");
        System.out.println("  Status     : " + status);
        System.out.println("  Items Ordered:");
        for (Product p : products) {
            System.out.printf("    - %-20s $%.2f%n", p.getName(), p.getPrice());
        }
        System.out.printf("  Order Total: $%.2f%n", orderTotal);
        System.out.println("  ====================================");
    }

    @Override
    public String toString() {
        return String.format("Order #%d | Customer: %s | Items: %d | Total: $%.2f | Status: %s",
                orderID, customer.getName(), products.size(), orderTotal, status);
    }
}

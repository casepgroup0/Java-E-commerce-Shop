package com.ecommerce;

import com.ecommerce.orders.Order;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int customerID;
    private String name;
    private List<Product> shoppingCart;
    private List<Order> orderHistory;

    public Customer(int customerID, String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }
        this.customerID = customerID;
        this.name = name.trim();
        this.shoppingCart = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
    }

    // Getters
    public int getCustomerID() { return customerID; }
    public String getName() { return name; }
    public List<Product> getShoppingCart() { return shoppingCart; }
    public List<Order> getOrderHistory() { return orderHistory; }

    // Add product to cart
    public void addToCart(Product product) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null.");
        if (!product.isAvailable()) {
            System.out.println("  Sorry, \"" + product.getName() + "\" is out of stock!");
            return;
        }
        shoppingCart.add(product);
        System.out.println("  Added \"" + product.getName() + "\" to " + name + "'s cart.");
    }

    // Remove product from cart by productID
    public void removeFromCart(int productID) {
        boolean removed = shoppingCart.removeIf(p -> p.getProductID() == productID);
        if (removed) {
            System.out.println("  Product ID " + productID + " removed from cart.");
        } else {
            System.out.println("  Product ID " + productID + " was not in the cart.");
        }
    }

    // Calculate total cost of items in cart
    public double calculateTotal() {
        return shoppingCart.stream().mapToDouble(Product::getPrice).sum();
    }

    // Place an order using current cart items
    public Order placeOrder(int orderID) {
        if (shoppingCart.isEmpty()) {
            throw new IllegalStateException("Cannot place order — shopping cart is empty!");
        }
        List<Product> orderedItems = new ArrayList<>(shoppingCart);
        Order order = new Order(orderID, this, orderedItems);
        orderHistory.add(order);
        shoppingCart.clear(); // Empty cart after ordering
        System.out.println("  Order #" + orderID + " placed successfully for " + name + "!");
        return order;
    }

    // Display cart contents
    public void displayCart() {
        System.out.println("\n  --- " + name + "'s Shopping Cart ---");
        if (shoppingCart.isEmpty()) {
            System.out.println("  Cart is empty.");
        } else {
            for (Product p : shoppingCart) {
                System.out.println("  - " + p.getName() + "  $" + String.format("%.2f", p.getPrice()));
            }
            System.out.printf("  Total: $%.2f%n", calculateTotal());
        }
    }

    @Override
    public String toString() {
        return String.format("Customer [ID: %d | Name: %s | Cart Items: %d | Orders: %d]",
                customerID, name, shoppingCart.size(), orderHistory.size());
    }
}

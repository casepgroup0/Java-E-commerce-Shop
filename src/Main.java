import com.ecommerce.Customer;
import com.ecommerce.Product;
import com.ecommerce.orders.Order;

public class Main {
    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("       Welcome to JavaShop E-Commerce      ");
        System.out.println("===========================================\n");

        // ── 1. Create some products ──────────────────────────────────────
        System.out.println(">>> Available Products in Store:");
        Product laptop    = new Product(101, "Laptop Pro 15",   1299.99, 10);
        Product headphones= new Product(102, "Noise-Cancel Headphones", 199.99, 25);
        Product mouse     = new Product(103, "Wireless Mouse",   49.99, 50);
        Product keyboard  = new Product(104, "Mechanical Keyboard", 89.99, 30);
        Product monitor   = new Product(105, "4K Monitor 27\"",  549.99,  5);

        Product[] catalog = { laptop, headphones, mouse, keyboard, monitor };
        for (Product p : catalog) {
            System.out.println("  " + p);
        }

        // ── 2. Create customers ──────────────────────────────────────────
        System.out.println("\n>>> Creating Customers:");
        Customer alice = new Customer(1, "Alice Johnson");
        Customer bob   = new Customer(2, "Bob Smith");
        System.out.println("  " + alice);
        System.out.println("  " + bob);

        // ── 3. Alice browses and adds to cart ────────────────────────────
        System.out.println("\n>>> Alice is shopping...");
        alice.addToCart(laptop);
        alice.addToCart(headphones);
        alice.addToCart(mouse);
        alice.displayCart();

        // Alice changes her mind and removes the mouse
        System.out.println("\n>>> Alice removes the mouse from her cart:");
        alice.removeFromCart(103);
        alice.displayCart();

        // ── 4. Bob browses and adds to cart ──────────────────────────────
        System.out.println("\n>>> Bob is shopping...");
        bob.addToCart(keyboard);
        bob.addToCart(monitor);
        bob.addToCart(mouse);
        bob.displayCart();

        // ── 5. Place orders ───────────────────────────────────────────────
        System.out.println("\n>>> Placing Orders:");
        Order aliceOrder = null;
        Order bobOrder   = null;

        try {
            aliceOrder = alice.placeOrder(1001);
            bobOrder   = bob.placeOrder(1002);
        } catch (IllegalStateException e) {
            System.out.println("  Order Error: " + e.getMessage());
        }

        // ── 6. Print order summaries ──────────────────────────────────────
        System.out.println("\n>>> Order Summaries:");
        if (aliceOrder != null) aliceOrder.printOrderSummary();
        if (bobOrder   != null) bobOrder.printOrderSummary();

        // ── 7. Update order statuses ──────────────────────────────────────
        System.out.println("\n>>> Updating Order Statuses:");
        if (aliceOrder != null) aliceOrder.updateStatus(Order.Status.CONFIRMED);
        if (aliceOrder != null) aliceOrder.updateStatus(Order.Status.SHIPPED);
        if (bobOrder   != null) bobOrder.updateStatus(Order.Status.CONFIRMED);

        // ── 8. Show customer info after shopping ──────────────────────────
        System.out.println("\n>>> Final Customer Info:");
        System.out.println("  " + alice);
        System.out.println("  " + bob);

        // ── 9. Demonstrate error handling — empty cart order ─────────────
        System.out.println("\n>>> Trying to order with an empty cart (error handling demo):");
        try {
            alice.placeOrder(9999); // Alice's cart is empty after the previous order
        } catch (IllegalStateException e) {
            System.out.println("  Caught Exception: " + e.getMessage());
        }

        // ── 10. Demonstrate invalid product creation ──────────────────────
        System.out.println("\n>>> Trying to create a product with negative price (validation demo):");
        try {
            Product badProduct = new Product(999, "Broken Item", -5.00, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught Exception: " + e.getMessage());
        }

        System.out.println("\n===========================================");
        System.out.println("         Thanks for shopping with us!      ");
        System.out.println("===========================================");
    }
}

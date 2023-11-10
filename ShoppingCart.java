import java.util.Stack;
import java.util.Scanner;

public class ShoppingCart {

    static class Product {
        String name;
        double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Product> cart = new Stack<>();

        System.out.println("Welcome to the Shopping Cart Management System!");
        System.out.print("Enter your username: ");
        String username = scanner.next();

        int choice;
        do {
            System.out.println("\n--- Shopping Cart Management System for " + username + " ---");
            System.out.println("1. Add to Cart");
            System.out.println("2. Update Cart");
            System.out.println("3. Delete from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Search in Cart");
            System.out.println("6. Confirm Order");
            System.out.println("7. Exit");

            System.out.print("Enter your choice (1-7): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addToCart(scanner, cart);
                    break;
                case 2:
                    updateCart(scanner, cart);
                    break;
                case 3:
                    deleteCart(scanner, cart);
                    break;
                case 4:
                    viewCart(cart);
                    break;
                case 5:
                    searchCart(scanner, cart);
                    break;
                case 6:
                    confirmOrder(scanner, cart, username);
                    break;
                case 7:
                    System.out.println("Exiting the system. Thank you for using our Shopping Cart!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        } while (choice != 7);
    }

    private static void addToCart(Scanner scanner, Stack<Product> cart) {
        System.out.println("\n--- Add to Cart ---");
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();

        cart.push(new Product(name, price));
        System.out.println("Product added to cart.");
    }

    private static void updateCart(Scanner scanner, Stack<Product> cart) {
        if (cart.isEmpty()) {
            System.out.println("\n--- Update Cart ---");
            System.out.println("Cart is empty. Unable to update.");
            return;
        }

        System.out.println("\n--- Update Cart ---");
        System.out.print("Enter product name to update: ");
        String name = scanner.next();

        boolean isUpdated = false;
        for (int i = 0; i < cart.size(); i++) {
            Product product = cart.get(i);
            if (product.name.equalsIgnoreCase(name)) {
                System.out.print("Enter new product price: ");
                double newPrice = scanner.nextDouble();
                product.price = newPrice;
                isUpdated = true;
                System.out.println("Product updated in cart.");
                break;
            }
        }

        if (!isUpdated) {
            System.out.println("Product not found in cart. Unable to update.");
        }
    }

    private static void deleteCart(Scanner scanner, Stack<Product> cart) {
        if (cart.isEmpty()) {
            System.out.println("\n--- Delete from Cart ---");
            System.out.println("Cart is empty. Unable to delete.");
            return;
        }

        System.out.println("\n--- Delete from Cart ---");
        System.out.print("Enter product name to delete: ");
        String name = scanner.next();

        boolean isDeleted = false;
        for (int i = 0; i < cart.size(); i++) {
            Product product = cart.get(i);
            if (product.name.equalsIgnoreCase(name)) {
                cart.remove(i);
                isDeleted = true;
                System.out.println("Product deleted from cart.");
                break;
            }
        }

        if (!isDeleted) {
            System.out.println("Product not found in cart. Unable to delete.");
        }
    }

    private static void viewCart(Stack<Product> cart) {
        System.out.println("\n--- View Cart ---");
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("Product Name - Price");
        double total = 0;
        for (Product product : cart) {
            System.out.println(product.name + " - " + product.price);
            total += product.price;
        }

        System.out.println("Total Price: " + total);
    }

    private static void searchCart(Scanner scanner, Stack<Product> cart) {
        if (cart.isEmpty()) {
            System.out.println("\n--- Search in Cart ---");
            System.out.println("Cart is empty. Nothing to search.");
            return;
        }

        System.out.println("\n--- Search in Cart ---");
        System.out.print("Enter product name to search: ");
        String name = scanner.next();

        boolean isFound = false;
        for (Product product : cart) {
            if (product.name.equalsIgnoreCase(name)) {
                System.out.println("Product found in cart.");
                System.out.println("Product Name - Price");
                System.out.println(product.name + " - " + product.price);
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            System.out.println("Product not found in cart.");
        }
    }

    private static void confirmOrder(Scanner scanner, Stack<Product> cart, String username) {
        System.out.println("\n--- Confirm Order ---");
        viewCart(cart);
        System.out.print("Confirm the order (Y/N): ");
        String confirmation = scanner.next().toLowerCase();

        if (confirmation.equals("y")) {
            double total = 0;
            for (Product product : cart) {
                total += product.price;
            }

            System.out.println("Order confirmed for " + username + " with a total of " + total);
            cart.clear();
            System.out.println("Exiting the system. Thank you for shopping with us!");
            System.exit(0);
        } else {
            System.out.println("Order not confirmed.");
        }
    }
}

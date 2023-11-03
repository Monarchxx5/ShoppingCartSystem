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

        int choice;
        do {
            System.out.println("\nShopping Cart Management System");
            System.out.println("1. Add to cart");
            System.out.println("2. Update cart");
            System.out.println("3. Delete cart");
            System.out.println("4. View cart");
            System.out.println("5. Search cart");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
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
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private static void addToCart(Scanner scanner, Stack<Product> cart) {
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();

        cart.push(new Product(name, price));
        System.out.println("Product added to cart.");
    }

    private static void updateCart(Scanner scanner, Stack<Product> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.print("Enter product name to update: ");
        String name = scanner.next();

        boolean isUpdated = false;
        for (int i = 0; i < cart.size(); i++) {
            Product product = cart.get(i);
            if (product.name.equalsIgnoreCase(name)) {
                System.out.print("Enter new product price: ");
                double price = scanner.nextDouble();
                product.price = price;
                isUpdated = true;
                break;
            }
        }

        if (isUpdated) {
            System.out.println("Product updated in cart.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    private static void deleteCart(Scanner scanner, Stack<Product> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.print("Enter product name to delete: ");
        String name = scanner.next();

        boolean isDeleted = false;
        for (int i = 0; i < cart.size(); i++) {
            Product product = cart.get(i);
            if (product.name.equalsIgnoreCase(name)) {
                cart.remove(i);
                isDeleted = true;
                break;
            }
        }

        if (isDeleted) {
            System.out.println("Product deleted from cart.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    private static void viewCart(Stack<Product> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("\nProduct Name - Price");
        double total = 0;
        for (Product product : cart) {
            System.out.println(product.name + " - " + product.price);
            total += product.price;
        }

        System.out.println("Total Price: " + total);
    }

    private static void searchCart(Scanner scanner, Stack<Product> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

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
}

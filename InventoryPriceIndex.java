import java.util.HashMap;

public class InventoryPriceIndex {

    static class Product {
        String productId;
        String name;
        int price;

        Product(String productId, String name, int price) {
            this.productId = productId;
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return productId + " - " + name + " - Rs." + price;
        }
    }

    static class AVLNode {
        Product product;
        AVLNode left;
        AVLNode right;
        int height;

        AVLNode(Product product) {
            this.product = product;
            height = 1;
        }
    }

    private final HashMap<String, Product> productMap =
            new HashMap<>();

    private AVLNode root;

    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(AVLNode node) {
        return node == null ? 0
                : height(node.left) - height(node.right);
    }

    private void updateHeight(AVLNode node) {
        node.height = 1 + Math.max(
                height(node.left),
                height(node.right)
        );
    }

    private int compare(Product first, Product second) {
        if (first.price != second.price) {
            return Integer.compare(first.price, second.price);
        }

        return first.productId.compareTo(second.productId);
    }

    private AVLNode rotateRight(AVLNode node) {
        AVLNode newRoot = node.left;
        AVLNode temporary = newRoot.right;

        newRoot.right = node;
        node.left = temporary;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    private AVLNode rotateLeft(AVLNode node) {
        AVLNode newRoot = node.right;
        AVLNode temporary = newRoot.left;

        newRoot.left = node;
        node.right = temporary;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    private AVLNode insert(AVLNode node, Product product) {
        if (node == null) {
            return new AVLNode(product);
        }

        if (compare(product, node.product) < 0) {
            node.left = insert(node.left, product);
        } else {
            node.right = insert(node.right, product);
        }

        updateHeight(node);

        int balance = getBalance(node);

        if (balance > 1 &&
                compare(product, node.left.product) < 0) {
            return rotateRight(node);
        }

        if (balance < -1 &&
                compare(product, node.right.product) > 0) {
            return rotateLeft(node);
        }

        if (balance > 1) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void addProduct(
            String productId,
            String name,
            int price
    ) {
        if (productMap.containsKey(productId)) {
            System.out.println("Product ID already exists.");
            return;
        }

        Product product =
                new Product(productId, name, price);

        productMap.put(productId, product);
        root = insert(root, product);
    }

    public void searchProduct(String productId) {
        Product product = productMap.get(productId);

        if (product == null) {
            System.out.println("Product not found.");
        } else {
            System.out.println("Product found: " + product);
        }
    }

    private void displayInOrder(AVLNode node) {
        if (node == null) {
            return;
        }

        displayInOrder(node.left);
        System.out.println(node.product);
        displayInOrder(node.right);
    }

    public void displayProductsByPrice() {
        System.out.println("\nProducts in increasing price order:");
        displayInOrder(root);
    }

    public static void main(String[] args) {
        InventoryPriceIndex inventory =
                new InventoryPriceIndex();

        inventory.addProduct("P101", "Keyboard", 1800);
        inventory.addProduct("P102", "Mouse", 700);
        inventory.addProduct("P103", "Monitor", 12500);
        inventory.addProduct("P104", "Webcam", 2400);

        inventory.searchProduct("P102");
        inventory.displayProductsByPrice();
    }
}

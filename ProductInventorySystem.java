import java.util.Arrays;

public class ProductInventorySystem {

    static class Product {
        int code;
        String name;
        String category;
        int[] weeklySales;
        int stock;

        Product(
                int code,
                String name,
                String category,
                int[] weeklySales,
                int stock
        ) {
            this.code = code;
            this.name = name;
            this.category = category;
            this.weeklySales = weeklySales;
            this.stock = stock;
        }

        int totalSales() {
            int total = 0;

            for (int sale : weeklySales) {
                total += sale;
            }

            return total;
        }

        @Override
        public String toString() {
            return code
                    + " | " + name
                    + " | " + category
                    + " | Sales " + Arrays.toString(weeklySales)
                    + " | Total " + totalSales()
                    + " | Stock " + stock;
        }
    }

    // ---------------- BST BY PRODUCT CODE ----------------

    static class ProductBSTNode {
        Product product;
        ProductBSTNode left;
        ProductBSTNode right;

        ProductBSTNode(Product product) {
            this.product = product;
        }
    }

    static class ProductBST {
        ProductBSTNode root;

        void insert(Product product) {
            root = insert(root, product);
        }

        private ProductBSTNode insert(
                ProductBSTNode node,
                Product product
        ) {
            if (node == null) {
                return new ProductBSTNode(product);
            }

            if (product.code < node.product.code) {
                node.left = insert(node.left, product);
            } else if (product.code > node.product.code) {
                node.right = insert(node.right, product);
            }

            return node;
        }

        Product search(int code) {
            ProductBSTNode current = root;

            while (current != null) {
                if (code == current.product.code) {
                    return current.product;
                }

                if (code < current.product.code) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        void displayByCode() {
            inorder(root);
        }

        private void inorder(ProductBSTNode node) {
            if (node != null) {
                inorder(node.left);
                System.out.println(node.product);
                inorder(node.right);
            }
        }
    }

    // ---------------- AVL BY STOCK ----------------

    static class StockAVLNode {
        Product product;
        int height;
        StockAVLNode left;
        StockAVLNode right;

        StockAVLNode(Product product) {
            this.product = product;
            this.height = 1;
        }
    }

    static class StockAVL {
        StockAVLNode root;

        void insert(Product product) {
            root = insert(root, product);
        }

        private StockAVLNode insert(
                StockAVLNode node,
                Product product
        ) {
            if (node == null) {
                return new StockAVLNode(product);
            }

            if (compare(product, node.product) < 0) {
                node.left = insert(node.left, product);
            } else {
                node.right = insert(node.right, product);
            }

            updateHeight(node);

            int balance = getBalance(node);

            if (balance > 1
                    && compare(product, node.left.product) < 0) {
                return rotateRight(node);
            }

            if (balance < -1
                    && compare(product, node.right.product) > 0) {
                return rotateLeft(node);
            }

            if (balance > 1
                    && compare(product, node.left.product) > 0) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }

            if (balance < -1
                    && compare(product, node.right.product) < 0) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }

            return node;
        }

        private int compare(Product first, Product second) {
            int stockComparison =
                    Integer.compare(first.stock, second.stock);

            if (stockComparison != 0) {
                return stockComparison;
            }

            return Integer.compare(first.code, second.code);
        }

        private int height(StockAVLNode node) {
            return node == null ? 0 : node.height;
        }

        private void updateHeight(StockAVLNode node) {
            node.height =
                    1 + Math.max(height(node.left), height(node.right));
        }

        private int getBalance(StockAVLNode node) {
            return node == null
                    ? 0
                    : height(node.left) - height(node.right);
        }

        private StockAVLNode rotateRight(StockAVLNode y) {
            StockAVLNode x = y.left;
            StockAVLNode middle = x.right;

            x.right = y;
            y.left = middle;

            updateHeight(y);
            updateHeight(x);

            return x;
        }

        private StockAVLNode rotateLeft(StockAVLNode x) {
            StockAVLNode y = x.right;
            StockAVLNode middle = y.left;

            y.left = x;
            x.right = middle;

            updateHeight(x);
            updateHeight(y);

            return y;
        }

        void displayLowestStockFirst() {
            inorder(root);
        }

        private void inorder(StockAVLNode node) {
            if (node != null) {
                inorder(node.left);

                System.out.println(
                        node.product.name
                                + " -> Stock: "
                                + node.product.stock
                );

                inorder(node.right);
            }
        }
    }

    // ---------------- LINKED LIST FOR ALERTS ----------------

    static class AlertNode {
        String message;
        AlertNode next;

        AlertNode(String message) {
            this.message = message;
        }
    }

    static class LowStockLinkedList {
        AlertNode head;
        AlertNode tail;

        void add(String message) {
            AlertNode newNode = new AlertNode(message);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        void display() {
            if (head == null) {
                System.out.println("No low-stock products.");
                return;
            }

            AlertNode current = head;

            while (current != null) {
                System.out.println("- " + current.message);
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {

        Product[] products = {
                new Product(
                        501,
                        "Laptop",
                        "Electronics",
                        new int[]{4, 5, 3, 6},
                        8
                ),
                new Product(
                        205,
                        "Notebook",
                        "Stationery",
                        new int[]{15, 12, 18, 20},
                        35
                ),
                new Product(
                        410,
                        "Headphones",
                        "Electronics",
                        new int[]{7, 9, 8, 10},
                        4
                ),
                new Product(
                        115,
                        "Water Bottle",
                        "Lifestyle",
                        new int[]{11, 13, 10, 12},
                        18
                ),
                new Product(
                        330,
                        "Backpack",
                        "Lifestyle",
                        new int[]{5, 7, 6, 8},
                        6
                )
        };

        ProductBST codeIndex = new ProductBST();
        StockAVL stockIndex = new StockAVL();
        LowStockLinkedList alerts =
                new LowStockLinkedList();

        for (Product product : products) {
            codeIndex.insert(product);
            stockIndex.insert(product);

            if (product.stock < 10) {
                alerts.add(
                        product.name
                                + " has only "
                                + product.stock
                                + " units left."
                );
            }
        }

        System.out.println(
                "PRODUCTS SORTED BY CODE (BST)"
        );
        codeIndex.displayByCode();

        System.out.println(
                "\nPRODUCTS SORTED BY STOCK (AVL)"
        );
        stockIndex.displayLowestStockFirst();

        int searchCode = 410;
        Product found = codeIndex.search(searchCode);

        System.out.println(
                "\nSEARCH PRODUCT CODE " + searchCode
        );

        if (found == null) {
            System.out.println("Product not found");
        } else {
            System.out.println(found);
        }

        System.out.println(
                "\nLOW-STOCK ALERTS (LINKED LIST)"
        );
        alerts.display();
    }
}

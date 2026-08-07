class Medicine {
    int medicineId;
    String name;
    int quantity;

    Medicine left, right;
    int height;

    Medicine(int medicineId, String name, int quantity) {

        this.medicineId = medicineId;
        this.name = name;
        this.quantity = quantity;

        height = 1;
    }
}

public class HospitalMedicineAVL {

    Medicine root;

    int height(Medicine node) {

        if (node == null) {
            return 0;
        }

        return node.height;
    }

    int getBalance(Medicine node) {

        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }

    Medicine rightRotate(Medicine y) {

        Medicine x = y.left;
        Medicine temp = x.right;

        x.right = y;
        y.left = temp;

        y.height = Math.max(
                height(y.left), height(y.right)) + 1;

        x.height = Math.max(
                height(x.left), height(x.right)) + 1;

        return x;
    }

    Medicine leftRotate(Medicine x) {

        Medicine y = x.right;
        Medicine temp = y.left;

        y.left = x;
        x.right = temp;

        x.height = Math.max(
                height(x.left), height(x.right)) + 1;

        y.height = Math.max(
                height(y.left), height(y.right)) + 1;

        return y;
    }

    Medicine insert(Medicine node,
                    int id, String name, int quantity) {

        if (node == null) {
            return new Medicine(id, name, quantity);
        }

        if (id < node.medicineId) {

            node.left = insert(
                    node.left, id, name, quantity);

        } else if (id > node.medicineId) {

            node.right = insert(
                    node.right, id, name, quantity);

        } else {

            return node;
        }

        node.height = 1 + Math.max(
                height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 &&
                id < node.left.medicineId) {

            return rightRotate(node);
        }

        if (balance < -1 &&
                id > node.right.medicineId) {

            return leftRotate(node);
        }

        if (balance > 1 &&
                id > node.left.medicineId) {

            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 &&
                id < node.right.medicineId) {

            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    Medicine search(Medicine node, int id) {

        if (node == null ||
                node.medicineId == id) {

            return node;
        }

        if (id < node.medicineId) {
            return search(node.left, id);
        }

        return search(node.right, id);
    }

    void inorder(Medicine node) {

        if (node != null) {

            inorder(node.left);

            System.out.println(
                    "Medicine ID: " + node.medicineId);

            System.out.println(
                    "Name: " + node.name);

            System.out.println(
                    "Quantity: " + node.quantity);

            System.out.println();

            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        HospitalMedicineAVL inventory =
                new HospitalMedicineAVL();

        inventory.root = inventory.insert(
                inventory.root, 30,
                "Paracetamol", 100);

        inventory.root = inventory.insert(
                inventory.root, 20,
                "Amoxicillin", 60);

        inventory.root = inventory.insert(
                inventory.root, 40,
                "Ibuprofen", 80);

        inventory.root = inventory.insert(
                inventory.root, 10,
                "Cetirizine", 50);

        System.out.println("Medicine Inventory:");
        inventory.inorder(inventory.root);

        Medicine result =
                inventory.search(inventory.root, 40);

        if (result != null) {

            System.out.println(
                    "Medicine Found: " + result.name);

        } else {

            System.out.println("Medicine Not Found");
        }
    }
}

class Doctor {
    int id;
    String name;
    String specialization;
    int patientsHandled;

    Doctor left, right;
    int height;

    Doctor(int id, String name, String specialization, int patientsHandled) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.patientsHandled = patientsHandled;

        height = 1;
    }
}

public class HospitalDoctorAVL {
    Doctor root;

    int height(Doctor node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    int max(int a, int b) {
        return Math.max(a, b);
    }

    Doctor rightRotate(Doctor y) {
        Doctor x = y.left;
        Doctor temp = x.right;

        x.right = y;
        y.left = temp;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Doctor leftRotate(Doctor x) {
        Doctor y = x.right;
        Doctor temp = y.left;

        y.left = x;
        x.right = temp;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalance(Doctor node) {
        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }

    Doctor insert(Doctor node, int id, String name,
                  String specialization, int patientsHandled) {

        if (node == null) {
            return new Doctor(id, name, specialization, patientsHandled);
        }

        if (id < node.id) {
            node.left = insert(node.left, id, name,
                    specialization, patientsHandled);
        } else if (id > node.id) {
            node.right = insert(node.right, id, name,
                    specialization, patientsHandled);
        } else {
            return node;
        }

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && id < node.left.id) {
            return rightRotate(node);
        }

        if (balance < -1 && id > node.right.id) {
            return leftRotate(node);
        }

        if (balance > 1 && id > node.left.id) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && id < node.right.id) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    Doctor search(Doctor node, int id) {
        if (node == null || node.id == id) {
            return node;
        }

        if (id < node.id) {
            return search(node.left, id);
        }

        return search(node.right, id);
    }

    void inorder(Doctor node) {
        if (node != null) {
            inorder(node.left);

            System.out.println("Doctor ID: " + node.id);
            System.out.println("Name: " + node.name);
            System.out.println("Specialization: " + node.specialization);
            System.out.println("Patients Handled: " + node.patientsHandled);
            System.out.println();

            inorder(node.right);
        }
    }

    public static void main(String[] args) {
        HospitalDoctorAVL hospital = new HospitalDoctorAVL();

        hospital.root = hospital.insert(
                hospital.root, 30, "Dr. Ravi", "Cardiology", 120);

        hospital.root = hospital.insert(
                hospital.root, 20, "Dr. Meena", "Neurology", 90);

        hospital.root = hospital.insert(
                hospital.root, 40, "Dr. Arjun", "Orthopedics", 150);

        hospital.root = hospital.insert(
                hospital.root, 10, "Dr. Priya", "Dermatology", 80);

        System.out.println("Doctors in Sorted Order:");
        hospital.inorder(hospital.root);

        Doctor result = hospital.search(hospital.root, 20);

        if (result != null) {
            System.out.println("Doctor Found: " + result.name);
        } else {
            System.out.println("Doctor Not Found");
        }
    }
}

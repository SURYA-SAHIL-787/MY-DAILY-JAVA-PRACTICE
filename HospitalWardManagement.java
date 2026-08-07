class Patient {
    int patientId;
    String name;
    String ward;
    Patient next;

    Patient(int patientId, String name, String ward) {

        this.patientId = patientId;
        this.name = name;
        this.ward = ward;
        this.next = null;
    }
}

class RecordNode {

    Patient patient;

    RecordNode left, right;
    int height;

    RecordNode(Patient patient) {

        this.patient = patient;
        this.height = 1;
    }
}

public class HospitalWardManagement {

    Patient head;
    RecordNode root;

    void admitPatient(int id, String name, String ward) {

        Patient newPatient =
                new Patient(id, name, ward);

        if (head == null) {

            head = newPatient;

        } else {

            Patient temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newPatient;
        }
    }

    void displayWardPatients() {

        Patient temp = head;

        while (temp != null) {

            System.out.println(
                    "Patient ID: " + temp.patientId);

            System.out.println(
                    "Name: " + temp.name);

            System.out.println(
                    "Ward: " + temp.ward);

            System.out.println();

            temp = temp.next;
        }
    }

    int height(RecordNode node) {

        if (node == null) {
            return 0;
        }

        return node.height;
    }

    int balance(RecordNode node) {

        if (node == null) {
            return 0;
        }

        return height(node.left) -
                height(node.right);
    }

    RecordNode rightRotate(RecordNode y) {

        RecordNode x = y.left;
        RecordNode temp = x.right;

        x.right = y;
        y.left = temp;

        y.height = Math.max(
                height(y.left),
                height(y.right)) + 1;

        x.height = Math.max(
                height(x.left),
                height(x.right)) + 1;

        return x;
    }

    RecordNode leftRotate(RecordNode x) {

        RecordNode y = x.right;
        RecordNode temp = y.left;

        y.left = x;
        x.right = temp;

        x.height = Math.max(
                height(x.left),
                height(x.right)) + 1;

        y.height = Math.max(
                height(y.left),
                height(y.right)) + 1;

        return y;
    }

    RecordNode insert(RecordNode node,
                      Patient patient) {

        if (node == null) {

            return new RecordNode(patient);
        }

        if (patient.patientId <
                node.patient.patientId) {

            node.left =
                    insert(node.left, patient);

        } else if (patient.patientId >
                node.patient.patientId) {

            node.right =
                    insert(node.right, patient);

        } else {

            return node;
        }

        node.height =
                1 + Math.max(
                        height(node.left),
                        height(node.right));

        int balance = balance(node);

        if (balance > 1 &&
                patient.patientId <
                        node.left.patient.patientId) {

            return rightRotate(node);
        }

        if (balance < -1 &&
                patient.patientId >
                        node.right.patient.patientId) {

            return leftRotate(node);
        }

        if (balance > 1 &&
                patient.patientId >
                        node.left.patient.patientId) {

            node.left = leftRotate(node.left);

            return rightRotate(node);
        }

        if (balance < -1 &&
                patient.patientId <
                        node.right.patient.patientId) {

            node.right = rightRotate(node.right);

            return leftRotate(node);
        }

        return node;
    }

    void dischargeFirstPatient() {

        if (head == null) {

            System.out.println(
                    "No patients available.");

            return;
        }

        Patient discharged = head;

        head = head.next;

        discharged.next = null;

        root = insert(root, discharged);

        System.out.println(
                discharged.name +
                " discharged and stored in records.");
    }

    void displayRecords(RecordNode node) {

        if (node != null) {

            displayRecords(node.left);

            System.out.println(
                    "ID: " +
                    node.patient.patientId);

            System.out.println(
                    "Name: " +
                    node.patient.name);

            System.out.println(
                    "Ward: " +
                    node.patient.ward);

            System.out.println();

            displayRecords(node.right);
        }
    }

    public static void main(String[] args) {

        HospitalWardManagement hospital =
                new HospitalWardManagement();

        hospital.admitPatient(
                303, "Akhil", "Ward A");

        hospital.admitPatient(
                301, "Sneha", "Ward B");

        hospital.admitPatient(
                305, "Vijay", "Ward C");

        System.out.println(
                "Currently Admitted Patients:");

        hospital.displayWardPatients();

        hospital.dischargeFirstPatient();
        hospital.dischargeFirstPatient();

        System.out.println(
                "\nDischarged Patient Records:");

        hospital.displayRecords(hospital.root);
    }
}

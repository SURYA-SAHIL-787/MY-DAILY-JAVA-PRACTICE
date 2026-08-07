class Patient {
    int id;
    String name;
    Patient next;

    Patient(int id, String name) {
        this.id = id;
        this.name = name;
        this.next = null;
    }
}

class Department {
    String departmentName;
    Patient head;

    Department(String departmentName) {
        this.departmentName = departmentName;
    }

    void addPatient(int id, String name) {
        Patient newPatient = new Patient(id, name);

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

    void removePatient(int id) {
        if (head == null) {
            return;
        }

        if (head.id == id) {
            head = head.next;
            return;
        }

        Patient temp = head;

        while (temp.next != null) {
            if (temp.next.id == id) {
                temp.next = temp.next.next;
                return;
            }

            temp = temp.next;
        }
    }

    void displayPatients() {
        System.out.println("Department: " + departmentName);

        Patient temp = head;

        while (temp != null) {
            System.out.println("Patient ID: " + temp.id);
            System.out.println("Patient Name: " + temp.name);
            System.out.println();

            temp = temp.next;
        }
    }

    int countPatients() {
        int count = 0;
        Patient temp = head;

        while (temp != null) {
            count++;
            temp = temp.next;
        }

        return count;
    }
}

public class DepartmentPatientTracker {

    public static void main(String[] args) {
        Department cardiology = new Department("Cardiology");

        cardiology.addPatient(101, "Rahul");
        cardiology.addPatient(102, "Sneha");
        cardiology.addPatient(103, "Ajay");

        cardiology.displayPatients();

        System.out.println(
                "Total Patients: " + cardiology.countPatients());

        cardiology.removePatient(102);

        System.out.println("\nAfter Removing Patient:");
        cardiology.displayPatients();

        System.out.println(
                "Total Patients: " + cardiology.countPatients());
    }
}

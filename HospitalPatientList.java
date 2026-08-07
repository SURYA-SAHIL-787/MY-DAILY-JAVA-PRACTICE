class Patient {
    int id;
    String name;
    int age;
    String disease;
    Patient next;

    Patient(int id, String name, int age, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
        this.next = null;
    }
}

public class HospitalPatientList {
    Patient head;

    void addPatient(int id, String name, int age, String disease) {
        Patient newPatient = new Patient(id, name, age, disease);

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

    void displayPatients() {
        Patient temp = head;

        while (temp != null) {
            System.out.println("ID: " + temp.id);
            System.out.println("Name: " + temp.name);
            System.out.println("Age: " + temp.age);
            System.out.println("Disease: " + temp.disease);
            System.out.println();

            temp = temp.next;
        }
    }

    void searchPatient(int id) {
        Patient temp = head;

        while (temp != null) {
            if (temp.id == id) {
                System.out.println("Patient Found");
                System.out.println("ID: " + temp.id);
                System.out.println("Name: " + temp.name);
                System.out.println("Age: " + temp.age);
                System.out.println("Disease: " + temp.disease);
                return;
            }

            temp = temp.next;
        }

        System.out.println("Patient Not Found");
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

    public static void main(String[] args) {
        HospitalPatientList hospital = new HospitalPatientList();

        hospital.addPatient(101, "Arun", 25, "Fever");
        hospital.addPatient(102, "Riya", 32, "Migraine");
        hospital.addPatient(103, "Kiran", 45, "Diabetes");

        System.out.println("Patient List:");
        hospital.displayPatients();

        hospital.searchPatient(102);

        hospital.removePatient(101);

        System.out.println("\nAfter Discharge:");
        hospital.displayPatients();
    }
}

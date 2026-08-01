import java.util.*;

class Patient {
    int id;
    String name;
    int priority;

    Patient(int id, String name, int priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
    }
}

class PatientRepository {
    HashMap<Integer, Patient> records = new HashMap<>();

    void save(Patient patient) {
        records.put(patient.id, patient);
    }

    Patient find(int id) {
        return records.get(id);
    }
}

class AppointmentService {

    PriorityQueue<Patient> queue =
            new PriorityQueue<>((a, b) -> b.priority - a.priority);

    PatientRepository repo = new PatientRepository();

    void registerPatient(Patient patient) {
        queue.offer(patient);
        repo.save(patient);
        System.out.println("Patient Registered Successfully.");
    }

    void treatPatient() {

        if (queue.isEmpty()) {
            System.out.println("No Patients Waiting.");
            return;
        }

        Patient patient = queue.poll();

        System.out.println("\nPatient Treated");
        System.out.println("ID : " + patient.id);
        System.out.println("Name : " + patient.name);
        System.out.println("Priority : " + patient.priority);
    }

    void searchPatient(int id) {

        Patient patient = repo.find(id);

        if (patient != null) {
            System.out.println("\nPatient Found");
            System.out.println("ID : " + patient.id);
            System.out.println("Name : " + patient.name);
            System.out.println("Priority : " + patient.priority);
        } else {
            System.out.println("Patient Not Found.");
        }
    }

    void displayWaiting() {

        if (queue.isEmpty()) {
            System.out.println("No Waiting Patients.");
            return;
        }

        System.out.println("\nWaiting Patients");

        for (Patient patient : queue) {
            System.out.println(patient.id + " - "
                    + patient.name + " - Priority: "
                    + patient.priority);
        }
    }
}

public class HospitalAppointmentSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AppointmentService service = new AppointmentService();

        while (true) {

            System.out.println("\n===== HOSPITAL APPOINTMENT SYSTEM =====");
            System.out.println("1. Register Patient");
            System.out.println("2. Treat Highest Priority Patient");
            System.out.println("3. Search Patient");
            System.out.println("4. Display Waiting Patients");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Patient ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Patient Name: ");
                    String name = sc.nextLine();

                    System.out.print("Emergency Priority (1-10): ");
                    int priority = sc.nextInt();

                    service.registerPatient(new Patient(id, name, priority));
                    break;

                case 2:
                    service.treatPatient();
                    break;

                case 3:
                    System.out.print("Enter Patient ID: ");
                    id = sc.nextInt();
                    service.searchPatient(id);
                    break;

                case 4:
                    service.displayWaiting();
                    break;

                case 5:
                    System.out.println("Thank You");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}

import java.util.ArrayList;
import java.util.Scanner;

class Doctor {
    private int doctorId;
    private String name;
    private String specialization;
    private boolean available;

    Doctor(int doctorId, String name, String specialization) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.available = true;
    }

    int getDoctorId() {
        return doctorId;
    }

    boolean isAvailable() {
        return available;
    }

    void bookAppointment() {
        available = false;
    }

    void displayDoctor() {
        String status = available ? "Available" : "Booked";

        System.out.println(
                doctorId + " | Dr. " + name +
                " | " + specialization +
                " | " + status
        );
    }
}

class Appointment {
    private String patientName;
    private Doctor doctor;

    Appointment(String patientName, Doctor doctor) {
        this.patientName = patientName;
        this.doctor = doctor;
    }

    void confirmAppointment() {
        doctor.bookAppointment();

        System.out.println("\nAppointment booked successfully.");
        System.out.println("Patient: " + patientName);
    }
}

public class HospitalAppointmentPortal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Doctor> doctors = new ArrayList<>();

        doctors.add(new Doctor(101, "Mehta", "Cardiologist"));
        doctors.add(new Doctor(102, "Priya", "Dermatologist"));
        doctors.add(new Doctor(103, "Kumar", "General Physician"));

        System.out.println("Available Doctors:");

        for (Doctor doctor : doctors) {
            doctor.displayDoctor();
        }

        System.out.print("\nEnter patient name: ");
        String patientName = scanner.nextLine();

        System.out.print("Enter doctor ID: ");
        int selectedId = scanner.nextInt();

        Doctor selectedDoctor = null;

        for (Doctor doctor : doctors) {
            if (doctor.getDoctorId() == selectedId) {
                selectedDoctor = doctor;
                break;
            }
        }

        if (selectedDoctor == null) {
            System.out.println("Doctor not found.");
        } else if (!selectedDoctor.isAvailable()) {
            System.out.println("Doctor is not available.");
        } else {
            Appointment appointment =
                    new Appointment(patientName, selectedDoctor);

            appointment.confirmAppointment();
        }

        scanner.close();
    }
}

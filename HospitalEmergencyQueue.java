import java.util.*;

class Patient implements Comparable<Patient> {
    private final String name;
    private final int severity;
    private final long arrivalOrder;

    Patient(String name, int severity, long arrivalOrder) {
        this.name = name;
        this.severity = severity;
        this.arrivalOrder = arrivalOrder;
    }

    @Override
    public int compareTo(Patient other) {
        if (severity != other.severity) {
            return Integer.compare(other.severity, severity);
        }
        return Long.compare(arrivalOrder, other.arrivalOrder);
    }

    @Override
    public String toString() {
        return name + " (severity " + severity + ")";
    }
}

class EmergencyRoom {
    private final PriorityQueue<Patient> waiting =
            new PriorityQueue<>();

    private long sequence = 0;

    void registerPatient(String name, int severity) {
        if (severity < 1 || severity > 5) {
            System.out.println("Severity must be between 1 and 5");
            return;
        }

        waiting.offer(new Patient(name, severity, sequence++));
        System.out.println(name + " registered");
    }

    void treatNextPatient() {
        Patient patient = waiting.poll();

        if (patient == null) {
            System.out.println("No patients waiting");
        } else {
            System.out.println("Treating " + patient);
        }
    }

    int waitingCount() {
        return waiting.size();
    }
}

public class HospitalEmergencyQueue {
    public static void main(String[] args) {
        EmergencyRoom room = new EmergencyRoom();

        room.registerPatient("Meera", 2);
        room.registerPatient("Arjun", 5);
        room.registerPatient("Kabir", 5);
        room.registerPatient("Neha", 3);

        room.treatNextPatient();
        room.treatNextPatient();

        System.out.println("Still waiting: " + room.waitingCount());
    }
}

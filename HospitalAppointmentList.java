class Appointment {
    int appointmentId;
    String patientName;
    String doctorName;
    String time;
    Appointment next;

    Appointment(int appointmentId, String patientName,
                String doctorName, String time) {
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.time = time;
        this.next = null;
    }
}

public class HospitalAppointmentList {

    Appointment head;

    void addAppointment(int id, String patient,
                        String doctor, String time) {

        Appointment newAppointment =
                new Appointment(id, patient, doctor, time);

        if (head == null) {
            head = newAppointment;
        } else {
            Appointment temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newAppointment;
        }
    }

    void displayAppointments() {

        Appointment temp = head;

        while (temp != null) {

            System.out.println("Appointment ID: " +
                    temp.appointmentId);

            System.out.println("Patient: " +
                    temp.patientName);

            System.out.println("Doctor: " +
                    temp.doctorName);

            System.out.println("Time: " +
                    temp.time);

            System.out.println();

            temp = temp.next;
        }
    }

    void cancelAppointment(int id) {

        if (head == null) {
            return;
        }

        if (head.appointmentId == id) {
            head = head.next;
            return;
        }

        Appointment temp = head;

        while (temp.next != null) {

            if (temp.next.appointmentId == id) {
                temp.next = temp.next.next;
                return;
            }

            temp = temp.next;
        }
    }

    public static void main(String[] args) {

        HospitalAppointmentList hospital =
                new HospitalAppointmentList();

        hospital.addAppointment(
                201, "Rahul", "Dr. Kumar", "10:00 AM");

        hospital.addAppointment(
                202, "Anjali", "Dr. Priya", "11:00 AM");

        hospital.addAppointment(
                203, "Kiran", "Dr. Ravi", "12:00 PM");

        System.out.println("Appointments:");
        hospital.displayAppointments();

        hospital.cancelAppointment(202);

        System.out.println("After Cancellation:");
        hospital.displayAppointments();
    }
}

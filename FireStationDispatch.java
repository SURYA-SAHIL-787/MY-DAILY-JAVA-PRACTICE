import java.util.*;

class Incident {
    int id;
    String location;
    int severity;

    Incident(int id, String location, int severity) {
        this.id = id;
        this.location = location;
        this.severity = severity;
    }
}

class FireTruck {
    int truckId;
    boolean available;

    FireTruck(int truckId) {
        this.truckId = truckId;
        this.available = true;
    }
}

class FireStation {
    Queue<FireTruck> trucks = new LinkedList<>();
    PriorityQueue<Incident> incidents;

    FireStation() {
        incidents = new PriorityQueue<>((a, b) -> b.severity - a.severity);
    }

    void addTruck(int id) {
        trucks.add(new FireTruck(id));
    }

    void reportIncident(int id, String location, int severity) {
        incidents.add(new Incident(id, location, severity));
    }

    void dispatchTruck() {
        if (trucks.isEmpty()) {
            System.out.println("No fire trucks available");
            return;
        }

        if (incidents.isEmpty()) {
            System.out.println("No incidents reported");
            return;
        }

        FireTruck truck = trucks.poll();
        Incident incident = incidents.poll();

        System.out.println("Truck " + truck.truckId + " dispatched to " + incident.location);
    }

    void returnTruck(int id) {
        trucks.add(new FireTruck(id));
        System.out.println("Truck " + id + " returned");
    }

    void showPendingIncidents() {
        for (Incident i : incidents) {
            System.out.println("Incident " + i.id + " Location: " + i.location + " Severity: " + i.severity);
        }
    }
}

public class FireStationDispatch {
    public static void main(String[] args) {
        FireStation station = new FireStation();

        station.addTruck(101);
        station.addTruck(102);
        station.addTruck(103);

        station.reportIncident(1, "Market Road", 5);
        station.reportIncident(2, "School Street", 8);
        station.reportIncident(3, "Railway Colony", 3);

        station.dispatchTruck();
        station.dispatchTruck();

        station.returnTruck(101);

        station.reportIncident(4, "Main City Mall", 9);

        station.dispatchTruck();

        station.showPendingIncidents();
    }
}

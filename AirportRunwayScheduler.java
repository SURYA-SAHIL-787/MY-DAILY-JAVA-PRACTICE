import java.util.*;

class PlaneRequest {
    String flightId;
    String type;
    int priority;

    PlaneRequest(String flightId, String type, int priority) {
        this.flightId = flightId;
        this.type = type;
        this.priority = priority;
    }
}

class RunwayScheduler {
    PriorityQueue<PlaneRequest> requests;

    RunwayScheduler() {
        requests = new PriorityQueue<>((a, b) -> b.priority - a.priority);
    }

    void addRequest(String flightId, String type, int priority) {
        requests.add(new PlaneRequest(flightId, type, priority));
        System.out.println(type + " request added for " + flightId);
    }

    void processNextRequest() {
        if (requests.isEmpty()) {
            System.out.println("No runway requests pending");
            return;
        }

        PlaneRequest request = requests.poll();
        System.out.println(request.flightId + " approved for " + request.type);
    }

    void showPendingRequests() {
        for (PlaneRequest request : requests) {
            System.out.println(request.flightId + " | " + request.type + " | Priority: " + request.priority);
        }
    }
}

public class AirportRunwayScheduler {
    public static void main(String[] args) {
        RunwayScheduler scheduler = new RunwayScheduler();

        scheduler.addRequest("AI203", "Landing", 9);
        scheduler.addRequest("6E455", "Takeoff", 5);
        scheduler.addRequest("UK812", "Landing", 8);
        scheduler.addRequest("SG331", "Takeoff", 4);
        scheduler.addRequest("QP771", "Landing", 7);

        scheduler.showPendingRequests();

        scheduler.processNextRequest();
        scheduler.processNextRequest();

        scheduler.addRequest("IX540", "Emergency Landing", 10);

        scheduler.processNextRequest();

        scheduler.showPendingRequests();
    }
}

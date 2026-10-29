import java.util.*;

class TrafficLane implements Comparable<TrafficLane> {
    String laneId;
    int vehicleCount;

    public TrafficLane(String laneId, int vehicleCount) {
        this.laneId = laneId;
        this.vehicleCount = vehicleCount;
    }

    @Override
    public int compareTo(TrafficLane other) {
        // Max-Heap behavior: Higher vehicle count gets higher priority (greedily served first)
        return Integer.compare(other.vehicleCount, this.vehicleCount);
    }
}

public class TrafficControlSystem {
    private PriorityQueue<TrafficLane> junctionQueue = new PriorityQueue<>();

    public void updateLaneTraffic(String laneId, int vehicleCount) {
        junctionQueue.removeIf(lane -> lane.laneId.equals(laneId)); // Update if already exists
        junctionQueue.offer(new TrafficLane(laneId, vehicleCount));
    }

    public void giveGreenSignal() {
        if (!junctionQueue.isEmpty()) {
            TrafficLane priorityLane = junctionQueue.poll();
            System.out.println("Green Signal given to Lane: " + priorityLane.laneId + " (Vehicle Congestion: " + priorityLane.vehicleCount + ")");
        }
    }

    public static void main(String[] args) {
        TrafficControlSystem controller = new TrafficControlSystem();
        controller.updateLaneTraffic("North-Street", 15);
        controller.updateLaneTraffic("East-Street", 45); // Highest congestion
        controller.updateLaneTraffic("West-Street", 8);

        controller.giveGreenSignal();
        controller.giveGreenSignal();
    }
}

import java.util.*;

class TrainNetwork {

    private HashMap<String, ArrayList<String>> graph;

    public TrainNetwork() {
        graph = new HashMap<>();
    }

    public void addStation(String station) {
        graph.putIfAbsent(station, new ArrayList<>());
    }

    public void addConnection(String station1, String station2) {

        addStation(station1);
        addStation(station2);

        graph.get(station1).add(station2);
        graph.get(station2).add(station1);
    }

    public void displayNetwork() {

        for (String station : graph.keySet()) {

            System.out.print(station + " -> ");

            for (String connectedStation : graph.get(station)) {
                System.out.print(connectedStation + " ");
            }

            System.out.println();
        }
    }
}

public class VandeBharatRouteGraph {

    public static void main(String[] args) {

        TrainNetwork network = new TrainNetwork();

        network.addConnection("Hyderabad", "Warangal");
        network.addConnection("Warangal", "Khammam");
        network.addConnection("Khammam", "Vijayawada");
        network.addConnection("Vijayawada", "Rajahmundry");
        network.addConnection("Rajahmundry", "Visakhapatnam");

        System.out.println("Vande Bharat Route Network:\n");

        network.displayNetwork();
    }
}

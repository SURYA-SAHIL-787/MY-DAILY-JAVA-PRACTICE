import java.util.*;

class RoadNetwork {

    private HashMap<String, ArrayList<String>> graph;

    public RoadNetwork() {
        graph = new HashMap<>();
    }

    public void addCity(String city) {

        graph.putIfAbsent(city, new ArrayList<>());
    }

    public void addRoad(String city1, String city2) {

        addCity(city1);
        addCity(city2);

        graph.get(city1).add(city2);
        graph.get(city2).add(city1);
    }

    public void displayNetwork() {

        for (String city : graph.keySet()) {

            System.out.print(city + " -> ");

            for (String connectedCity : graph.get(city)) {

                System.out.print(connectedCity + " ");
            }

            System.out.println();
        }
    }
}

public class AutomobileRoadNetwork {

    public static void main(String[] args) {

        RoadNetwork network = new RoadNetwork();

        network.addRoad("Chennai", "Bangalore");
        network.addRoad("Chennai", "Pondicherry");
        network.addRoad("Bangalore", "Mysore");
        network.addRoad("Bangalore", "Hyderabad");
        network.addRoad("Mysore", "Coimbatore");

        System.out.println("Automobile Road Network:\n");

        network.displayNetwork();
    }
}

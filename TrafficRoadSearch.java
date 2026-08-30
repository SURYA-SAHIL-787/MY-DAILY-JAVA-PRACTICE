import java.util.Arrays;

class RoadSearch {

    String[] roads;

    RoadSearch(String[] roads) {
        this.roads = roads;
        Arrays.sort(this.roads);
    }

    int search(String roadName) {

        int low = 0;
        int high = roads.length - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            int result =
                roads[mid].compareToIgnoreCase(roadName);

            if (result == 0) {
                return mid;
            }

            if (result < 0) {
                low = mid + 1;
            }

            else {
                high = mid - 1;
            }
        }

        return -1;
    }
}

public class TrafficRoadSearch {

    public static void main(String[] args) {

        String[] roads = {
            "MG Road",
            "Bannerghatta Road",
            "Hosur Road",
            "Mysore Road",
            "Outer Ring Road"
        };

        RoadSearch search = new RoadSearch(roads);

        String road = "Hosur Road";

        int result = search.search(road);

        if (result != -1) {
            System.out.println(
                road + " found in traffic database."
            );
        }

        else {
            System.out.println(
                road + " not found."
            );
        }
    }
}

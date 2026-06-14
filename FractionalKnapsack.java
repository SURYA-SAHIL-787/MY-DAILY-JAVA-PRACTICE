import java.util.*;

public class FractionalKnapsack {
    public static void main(String[] args) {
        int[] value = {60, 100, 120};
        int[] weight = {10, 20, 30};
        int capacity = 50;

        int n = value.length;

        double[][] items = new double[n][3];

        for (int i = 0; i < n; i++) {
            items[i][0] = value[i];
            items[i][1] = weight[i];
            items[i][2] = (double) value[i] / weight[i];
        }

        Arrays.sort(items, (a, b) -> Double.compare(b[2], a[2]));

        double totalValue = 0;

        for (int i = 0; i < n; i++) {
            if (capacity >= items[i][1]) {
                capacity -= items[i][1];
                totalValue += items[i][0];
            } else {
                totalValue += items[i][2] * capacity;
                break;
            }
        }

        System.out.println("Maximum value: " + totalValue);
    }
}

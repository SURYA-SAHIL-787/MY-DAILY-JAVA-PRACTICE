import java.util.*;

public class RailwayMinimumCost {

    static int[] cost;
    static int[] dp;

    static int minimumCost(int n) {

        // Base case
        if (n == 0) {
            return cost[0];
        }

        if (n == 1) {
            return cost[1];
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        dp[n] = cost[n] +
                Math.min(
                        minimumCost(n - 1),
                        minimumCost(n - 2));

        return dp[n];
    }

    public static void main(String[] args) {

        cost = new int[]{
                10, 15, 20, 5, 10, 5
        };

        int n = cost.length;

        dp = new int[n];

        Arrays.fill(dp, -1);

        int answer =
                minimumCost(n - 1);

        System.out.println(
                "Minimum railway travel cost = ₹"
                        + answer);
    }
}

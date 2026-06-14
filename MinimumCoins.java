import java.util.*;

public class MinimumCoins {
    public static void main(String[] args) {
        int amount = 93;

        int[] coins = {2000, 500, 200, 100, 50, 20, 10, 5, 2, 1};

        int count = 0;

        for (int coin : coins) {
            while (amount >= coin) {
                amount -= coin;
                count++;
                System.out.println("Use coin: " + coin);
            }
        }

        System.out.println("Minimum coins needed: " + count);
    }
}

import java.util.*;

public class MinimumIncrementToMakeArrayUnique {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int moves = 0;

        for (int i = 1; i < n; i++) {
            if (arr[i] <= arr[i - 1]) {
                int needed = arr[i - 1] + 1;
                moves += needed - arr[i];
                arr[i] = needed;
            }
        }

        System.out.println(moves);
    }
}

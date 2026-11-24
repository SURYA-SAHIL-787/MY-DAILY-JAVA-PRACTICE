import java.util.Arrays;

public class MaximizeShipmentOrder {

    public static int[] maximizeShipmentOrder(int[] arr, int k) {
        int n = arr.length;
        int[] best = null;

        for (int i = 0; i <= n - k; i++) {
            int[] temp = arr.clone();

            Arrays.sort(temp, i, i + k);

            if (best == null || isGreater(temp, best)) {
                best = temp;
            }
        }

        return best;
    }

    private static boolean isGreater(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return a[i] > b[i];
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] shipmentOrder = {5, 1, 4, 3, 2};
        int k = 3;

        int[] result = maximizeShipmentOrder(shipmentOrder, k);

        System.out.println(Arrays.toString(result));
    }
}

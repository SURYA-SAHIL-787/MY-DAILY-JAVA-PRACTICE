import java.util.*;

public class PrintSubsets {

    static void findSubsets(int[] arr, int index, ArrayList<Integer> list) {
        if (index == arr.length) {
            System.out.println(list);
            return;
        }

        // Include current element
        list.add(arr[index]);
        findSubsets(arr, index + 1, list);

        // Exclude current element
        list.remove(list.size() - 1);
        findSubsets(arr, index + 1, list);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        ArrayList<Integer> list = new ArrayList<>();

        System.out.println("All subsets are:");
        findSubsets(arr, 0, list);
    }
}

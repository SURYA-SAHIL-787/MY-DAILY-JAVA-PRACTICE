import java.util.*;

public class UniqueIntersection {
    public static void main(String[] args) {
        int[] a = {1, 2, 2, 3, 4};
        int[] b = {2, 2, 4, 6};

        HashSet<Integer> setA = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();

        for (int num : a) {
            setA.add(num);
        }

        for (int num : b) {
            if (setA.contains(num)) {
                result.add(num);
            }
        }

        System.out.println("Intersection: " + result);
    }
}

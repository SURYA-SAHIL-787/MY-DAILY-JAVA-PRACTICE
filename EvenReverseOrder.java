import java.util.*;

public class EvenReverseOrder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        int start = Math.max(a, b);
        int end = Math.min(a, b);

        for (int i = start; i >= end; i--) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }
    }
}

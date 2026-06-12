import java.util.*;

public class CompareVersionNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String version1 = sc.nextLine();
        String version2 = sc.nextLine();

        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int n = Math.max(v1.length, v2.length);

        for (int i = 0; i < n; i++) {
            int a = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int b = i < v2.length ? Integer.parseInt(v2[i]) : 0;

            if (a > b) {
                System.out.println(1);
                return;
            } else if (a < b) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(0);
    }
}

import java.util.*;

public class StringPermutations {

    static void printPermutations(String str, String answer) {
        if (str.length() == 0) {
            System.out.println(answer);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            String left = str.substring(0, i);
            String right = str.substring(i + 1);

            printPermutations(left + right, answer + ch);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String str = sc.next();

        System.out.println("All permutations are:");
        printPermutations(str, "");
    }
}

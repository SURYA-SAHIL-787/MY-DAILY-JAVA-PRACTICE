// int fact = 0;

// for (int i = 1; i <= 5; i++) {
//     fact = fact * i;
// }
// System.out.println(fact);

public class FactorialDebug {
    public static void main(String[] args) {
        int n = 5;
        int fact = 1;

        for (int i = 1; i <= n; i++) {
            fact = fact * i;
        }

        System.out.println("Factorial: " + fact);
    }
}

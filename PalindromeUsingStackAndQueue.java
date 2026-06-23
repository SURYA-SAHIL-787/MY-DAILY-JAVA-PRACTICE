import java.util.*;

public class PalindromeUsingStackAndQueue {
    public static boolean isPalindrome(String str) {
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();

        str = str.toLowerCase();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                stack.push(ch);
                queue.add(ch);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.pop() != queue.remove()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String str = sc.nextLine();

        if (isPalindrome(str)) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }

        sc.close();
    }
}

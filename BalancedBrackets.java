import java.util.ArrayDeque;
import java.util.Deque;

public class BalancedBrackets {
    public static boolean isBalanced(String brackets) {
        Deque<Character> expected = new ArrayDeque<>();

        for (int i = 0; i < brackets.length(); i++) {
            char bracket = brackets.charAt(i);
            if (bracket == '(') {
                expected.push(')');
            } else if (bracket == '[') {
                expected.push(']');
            } else if (bracket == '{') {
                expected.push('}');
            } else if (expected.isEmpty() || expected.pop() != bracket) {
                return false;
            }
        }

        return expected.isEmpty();
    }
}

 import java.util.*;

public class PalindromePartitioning {
    static List<List<String>> ans = new ArrayList<>();

    static boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }

    static void backtrack(String s, int start, List<String> temp) {
        if (start == s.length()) {
            ans.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                temp.add(s.substring(start, i + 1));
                backtrack(s, i + 1, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        backtrack(s, 0, new ArrayList<>());

        for (List<String> list : ans) {
            System.out.println(list);
        }
    }
}

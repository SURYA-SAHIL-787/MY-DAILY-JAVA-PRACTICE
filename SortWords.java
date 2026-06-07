import java.util.*;

public class SortWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String[] words = sc.nextLine().split("\\s+");
        Arrays.sort(words);
        System.out.println("Sorted words: " + Arrays.toString(words));
    }
}

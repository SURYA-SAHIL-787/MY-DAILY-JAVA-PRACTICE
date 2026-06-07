import java.util.HashMap;

public class WordFrequencySearch {
    public static void main(String[] args) {
        String sentence = "java is easy and java is powerful";
        String searchWord = "java";

        HashMap<String, Integer> map = new HashMap<>();

        String[] words = sentence.toLowerCase().split("\\s+");

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        System.out.println(searchWord + " appears " + map.getOrDefault(searchWord, 0) + " times");
    }
}

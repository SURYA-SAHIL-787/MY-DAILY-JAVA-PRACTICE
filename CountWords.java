public class CountWords {
    public static void main(String[] args) {
        String s = "Java is very powerful";
        String[] words = s.trim().split("\\s+");

        System.out.println("Word count: " + words.length);
    }
}

import java.util.ArrayList;

public class ArrayListExceptionDemo {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

        list.add("Java");
        list.add("Python");
        list.add("C++");

        try {
            System.out.println(list.get(5));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index accessed.");
            System.out.println(e);
        }
    }
}

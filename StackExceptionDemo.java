import java.util.Stack;

class StackUnderflowException extends Exception {
    public StackUnderflowException(String message) {
        super(message);
    }
}

public class StackExceptionDemo {

    static void popElement(Stack<Integer> stack) throws StackUnderflowException {
        if (stack.isEmpty()) {
            throw new StackUnderflowException("Stack is empty. Cannot pop.");
        }
        System.out.println("Popped: " + stack.pop());
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        try {
            popElement(stack);
        } catch (StackUnderflowException e) {
            System.out.println(e.getMessage());
        }
    }
}

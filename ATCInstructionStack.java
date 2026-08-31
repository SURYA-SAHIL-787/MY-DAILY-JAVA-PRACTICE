import java.util.Stack;

public class ATCInstructionStack {

    public static void main(String[] args) {

        Stack<String> instructions = new Stack<>();

        instructions.push("AI101 - Maintain 5000 feet");
        instructions.push("6E202 - Turn left");
        instructions.push("UK303 - Prepare for landing");

        System.out.println("ATC Instructions:");

        for (String instruction : instructions) {
            System.out.println(instruction);
        }

        String latest = instructions.pop();

        System.out.println("\nLatest Instruction Removed:");
        System.out.println(latest);
    }
}

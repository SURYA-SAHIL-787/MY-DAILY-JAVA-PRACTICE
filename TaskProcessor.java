import java.util.LinkedList;
import java.util.Queue;

class InvalidTaskException extends RuntimeException {
    public InvalidTaskException(String message) {
        super(message);
    }
}

abstract class Task {
    abstract void execute();
}

class PrintTask extends Task {
    private String name;

    public PrintTask(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidTaskException("Task name cannot be empty");
        }
        this.name = name;
    }

    @Override
    void execute() {
        System.out.println(Thread.currentThread().getName() + " executing: " + name);
    }
}

public class TaskProcessor {
    public static void main(String[] args) {
        Queue<Task> taskQueue = new LinkedList<>();
        try {
            taskQueue.add(new PrintTask("Compile"));
            taskQueue.add(new PrintTask("Test"));
            taskQueue.add(new PrintTask(""));
        } catch (InvalidTaskException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        while (!taskQueue.isEmpty()) {
            Task t = taskQueue.poll();
            new Thread(t::execute).start();
        }
    }
}

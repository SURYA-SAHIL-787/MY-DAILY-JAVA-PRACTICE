
import java.util.*;

public class BackendTaskScheduler {

    static class Task {
        private final String taskName;
        private final int priority;
        private final long sequence;

        public Task(String taskName, int priority, long sequence) {
            this.taskName = taskName;
            this.priority = priority;
            this.sequence = sequence;
        }

        public String getTaskName() {
            return taskName;
        }

        public int getPriority() {
            return priority;
        }

        public long getSequence() {
            return sequence;
        }
    }

    private final PriorityQueue<Task> taskQueue =
            new PriorityQueue<>((a, b) -> {

                int compare = Integer.compare(
                        b.getPriority(), a.getPriority());

                if (compare != 0) return compare;

                return Long.compare(
                        a.getSequence(), b.getSequence());
            });

    private long sequenceCounter = 0;

    public void addTask(String name, int priority) {
        taskQueue.offer(
                new Task(name, priority, sequenceCounter++)
        );
    }

    public void executeTasks() {
        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.poll();

            System.out.println("Executing: "
                    + task.getTaskName()
                    + " | Priority: " + task.getPriority());
        }
    }

    public static void main(String[] args) {
        BackendTaskScheduler scheduler =
                new BackendTaskScheduler();

        scheduler.addTask("Send Email", 2);
        scheduler.addTask("Process Payment", 5);
        scheduler.addTask("Generate Report", 1);
        scheduler.addTask("Update Inventory", 4);
        scheduler.addTask("Send Notification", 2);

        scheduler.executeTasks();
    }
}

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

class InvalidPriorityException extends Exception {
    InvalidPriorityException(String message) {
        super(message);
    }
}

class TaskFailureException extends Exception {
    TaskFailureException(String message) {
        super(message);
    }
}

class ScheduledTask implements Comparable<ScheduledTask> {
    private final int id;
    private final int priority;
    private final long deadline;
    private final AtomicInteger attempts;

    public ScheduledTask(int id, int priority, long deadline) throws InvalidPriorityException {
        if (priority < 1 || priority > 10) {
            throw new InvalidPriorityException("Priority must be between 1 and 10");
        }

        this.id = id;
        this.priority = priority;
        this.deadline = deadline;
        this.attempts = new AtomicInteger(0);
    }

    public int getId() {
        return id;
    }

    public int getAttempts() {
        return attempts.get();
    }

    public int incrementAttempts() {
        return attempts.incrementAndGet();
    }

    @Override
    public int compareTo(ScheduledTask other) {
        if (this.priority != other.priority) {
            return other.priority - this.priority;
        }

        return Long.compare(this.deadline, other.deadline);
    }

    public void execute() throws TaskFailureException {
        if (System.currentTimeMillis() > deadline) {
            throw new TaskFailureException("Task expired: " + id);
        }

        if (id % 4 == 0 && getAttempts() < 2) {
            throw new TaskFailureException("Artificial task failure: " + id);
        }

        System.out.println(Thread.currentThread().getName() + " executed task " + id);
    }
}

class PriorityTaskScheduler {
    private final PriorityBlockingQueue<ScheduledTask> queue;
    private final ExecutorService workers;
    private volatile boolean running;

    public PriorityTaskScheduler(int workerCount) {
        this.queue = new PriorityBlockingQueue<>();
        this.workers = Executors.newFixedThreadPool(workerCount);
        this.running = true;
    }

    public void submitTask(ScheduledTask task) {
        queue.offer(task);
    }

    public void start() {
        for (int i = 0; i < 3; i++) {
            workers.submit(() -> {
                while (running || !queue.isEmpty()) {
                    try {
                        ScheduledTask task = queue.poll(1, TimeUnit.SECONDS);

                        if (task == null) {
                            continue;
                        }

                        try {
                            task.execute();
                        } catch (TaskFailureException e) {
                            System.out.println(e.getMessage());

                            if (task.incrementAttempts() <= 3) {
                                queue.offer(task);
                            } else {
                                System.out.println("Task permanently failed: " + task.getId());
                            }
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
    }

    public void shutdown() {
        running = false;
        workers.shutdown();
    }
}

public class ThreadSafePriorityTaskScheduler {
    public static void main(String[] args) throws Exception {
        PriorityTaskScheduler scheduler = new PriorityTaskScheduler(3);
        scheduler.start();

        long future = System.currentTimeMillis() + 10000;

        scheduler.submitTask(new ScheduledTask(1, 5, future));
        scheduler.submitTask(new ScheduledTask(2, 9, future));
        scheduler.submitTask(new ScheduledTask(3, 3, future));
        scheduler.submitTask(new ScheduledTask(4, 10, future));
        scheduler.submitTask(new ScheduledTask(5, 8, future));

        Thread.sleep(3000);
        scheduler.shutdown();
    }
}

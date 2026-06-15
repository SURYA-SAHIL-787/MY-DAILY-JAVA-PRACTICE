import java.util.*;

public class JobSequencing {

    static class Job {
        int deadline;
        int profit;

        Job(int deadline, int profit) {
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static int maxProfit(Job[] jobs) {
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        int maxDeadline = 0;

        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        boolean[] slot = new boolean[maxDeadline + 1];

        int totalProfit = 0;

        for (Job job : jobs) {
            for (int time = job.deadline; time >= 1; time--) {
                if (!slot[time]) {
                    slot[time] = true;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        Job[] jobs = {
            new Job(2, 100),
            new Job(1, 19),
            new Job(2, 27),
            new Job(1, 25),
            new Job(3, 15)
        };

        System.out.println(maxProfit(jobs));
    }
}

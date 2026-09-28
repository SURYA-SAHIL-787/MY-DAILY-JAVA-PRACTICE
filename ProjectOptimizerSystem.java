import java.util.*;

// 1. Task Class
class Task {
    private int id;
    private int cost;
    private int reward;
    private String department;
    private List<Integer> prerequisites;

    public Task(int id, int cost, int reward, String department, List<Integer> prerequisites) {
        this.id = id;
        this.cost = cost;
        this.reward = reward;
        this.department = department;
        this.prerequisites = prerequisites != null ? prerequisites : new ArrayList<>();
    }

    public int getId() { return id; }
    public int getCost() { return cost; }
    public int getReward() { return reward; }
    public String getDepartment() { return department; }
    public List<Integer> getPrerequisites() { return prerequisites; }
}

// 2. ProjectAnalyzer Class
class ProjectAnalyzer {
    private Map<Integer, Task> taskMap;

    public ProjectAnalyzer(List<Task> tasks) {
        taskMap = new HashMap<>();
        for (Task t : tasks) {
            taskMap.put(t.getId(), t);
        }
    }

    public boolean validateSequence(List<Integer> selectedTaskIds) {
        Set<Integer> completed = new HashSet<>();
        for (int id : selectedTaskIds) {
            Task task = taskMap.get(id);
            if (task == null) return false;
            for (int pre : task.getPrerequisites()) {
                if (!completed.contains(pre)) {
                    return false; // Prerequisite not satisfied
                }
            }
            completed.add(id);
        }
        return true;
    }
}

// 3. PathOptimizer Class
class PathOptimizer {
    private int maxReward = 0;
    private List<Integer> bestPath = new ArrayList<>();
    private Map<String, Integer> memo = new HashMap<>();

    public List<Integer> optimizeProject(List<Task> tasks, int totalBudget) {
        maxReward = 0;
        bestPath.clear();
        memo.clear();
        
        // Sort tasks or index them for efficient lookup
        Map<Integer, Task> taskMap = new HashMap<>();
        for (Task t : tasks) taskMap.put(t.getId(), t);

        backtrack(tasks, 0, 0, 0, new ArrayList<>(), new HashSet<>(), taskMap, totalBudget);
        return bestPath;
    }

    private void backtrack(List<Task> tasks, int index, int currentCost, int currentReward, 
                           List<Integer> currentPath, Set<Integer> completedSet, 
                           Map<Integer, Task> taskMap, int totalBudget) {
        if (currentCost > totalBudget) return;

        if (currentReward > maxReward) {
            maxReward = currentReward;
            bestPath = new ArrayList<>(currentPath);
        }

        for (int i = index; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            
            // Check if prerequisites are met
            boolean prereqMet = true;
            for (int pre : task.getPrerequisites()) {
                if (!completedSet.contains(pre)) {
                    prereqMet = false;
                    break;
                }
            }

            if (prereqMet && currentCost + task.getCost() <= totalBudget) {
                currentPath.add(task.getId());
                completedSet.add(task.getId());
                
                backtrack(tasks, i + 1, currentCost + task.getCost(), currentReward + task.getReward(), 
                          currentPath, completedSet, taskMap, totalBudget);
                
                completedSet.remove(task.getId());
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }
}

// Execution Runner
public class ProjectOptimizerSystem {
    public static void main(String[] args) {
        List<Task> tasks = Arrays.asList(
            new Task(1, 10, 50, "Engineering", new ArrayList<>()),
            new Task(2, 20, 90, "Engineering", Arrays.asList(1)),
            new Task(3, 15, 60, "Marketing", new ArrayList<>()),
            new Task(4, 25, 120, "Marketing", Arrays.asList(2, 3))
        );

        ProjectAnalyzer analyzer = new ProjectAnalyzer(tasks);
        PathOptimizer optimizer = new PathOptimizer();

        List<Integer> optimalPath = optimizer.optimizeProject(tasks, 50);
        System.out.println("Optimal Task Path IDs: " + optimalPath);
        System.out.println("Is sequence valid? " + analyzer.validateSequence(optimalPath));
    }
}

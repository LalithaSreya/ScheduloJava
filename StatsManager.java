import java.util.List;

public class StatsManager {

    public int totalTasks(List<Task> tasks) {
        return tasks.size();
    }

    public int completedTasks(List<Task> tasks) {
        int count = 0;
        for (Task t : tasks) {
            if (t.isCompleted()) count++;
        }
        return count;
    }

    public int pendingTasks(List<Task> tasks) {
        return totalTasks(tasks) - completedTasks(tasks);
    }

    public double completionRate(List<Task> tasks) {
        if (tasks.isEmpty()) return 0.0;
        return (completedTasks(tasks) * 100.0) / tasks.size();
    }
}

import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class TaskManager {

    private static final String TASKS_FILE = "web/data/tasks.json";
    private static final String HISTORY_FILE = "web/data/history.json";
    private static final String STATS_FILE = "web/data/stats.json";

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Read list from JSON file
    private static <T> List<T> readList(String path, Class<T> clazz) {
        try {
            File file = new File(path);
            if (!file.exists()) return new ArrayList<>();

            Reader reader = new FileReader(path);
            List<T> list = gson.fromJson(reader, TypeToken.getParameterized(List.class, clazz).getType());
            reader.close();
            return list == null ? new ArrayList<>() : list;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Write list to JSON file
    private static <T> void writeList(String path, List<T> list) {
        try {
            Writer writer = new FileWriter(path);
            gson.toJson(list, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Loaders
    public static List<Task> loadTasks() { return readList(TASKS_FILE, Task.class); }
    public static List<Task> loadHistory() { return readList(HISTORY_FILE, Task.class); }

    // Savers
    public static void saveTasks(List<Task> tasks) { writeList(TASKS_FILE, tasks); }
    public static void saveHistory(List<Task> history) { writeList(HISTORY_FILE, history); }


    // Update stats.json
    public static void updateStats(List<Task> tasks) {
        int high = 0, medium = 0, low = 0;

        for (Task t : tasks) {
            switch (t.priority.toLowerCase()) {
                case "high": high++; break;
                case "medium": medium++; break;
                case "low": low++; break;
            }
        }

        JsonObject obj = new JsonObject();
        obj.addProperty("High", high);
        obj.addProperty("Medium", medium);
        obj.addProperty("Low", low);

        try {
            Writer writer = new FileWriter(STATS_FILE);
            gson.toJson(obj, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ---- OPERATIONS ----

    public static void addTask(Task t) {
        List<Task> tasks = loadTasks();
        tasks.add(t);
        saveTasks(tasks);
        updateStats(tasks);
        System.out.println("✓ Task added!");
    }

    public static void deleteTask(int index) {
        List<Task> tasks = loadTasks();
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task number!");
            return;
        }
        tasks.remove(index);
        saveTasks(tasks);
        updateStats(tasks);
        System.out.println("✓ Task deleted!");
    }

    public static void completeTask(int index) {
        List<Task> tasks = loadTasks();
        List<Task> history = loadHistory();

        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task number!");
            return;
        }

        Task t = tasks.remove(index);
        t.completed = true;

        // Move to history.json
        history.add(t);

        saveTasks(tasks);
        saveHistory(history);
        updateStats(tasks);

        System.out.println("✓ Task marked as completed!");
    }

    public static void listTasks() {
        List<Task> tasks = loadTasks();

        if (tasks.isEmpty()) {
            System.out.println("No tasks found!");
            return;
        }

        System.out.println("\nYour Tasks:");
        int i = 1;
        for (Task t : tasks) {
            System.out.println(i++ + ". " + t.title + " [" + t.priority + "] - " + t.deadline);
        }
    }
}
public class Task {
    public String title;
    public String description;
    public String category;
    public String priority;
    public String mood;
    public String deadline;
    public boolean canPair;
    public boolean completed;

    public Task(String title, String description, String category, String priority,
                String mood, String deadline, boolean canPair) {

        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.mood = mood;
        this.deadline = deadline;
        this.canPair = canPair;
        this.completed = false;
    }
}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== SCHEDULO MENU =====");
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int ch = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (ch) {
                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();

                    System.out.print("Description: ");
                    String desc = sc.nextLine();

                    System.out.print("Category (College/Personal/Work/etc): ");
                    String cat = sc.nextLine();

                    System.out.print("Priority (High/Medium/Low): ");
                    String pr = sc.nextLine();

                    System.out.print("Mood: ");
                    String mood = sc.nextLine();

                    System.out.print("Deadline (YYYY-MM-DD): ");
                    String d = sc.nextLine();

                    System.out.print("Can pair? (true/false): ");
                    boolean pair = sc.nextBoolean();

                    Task t = new Task(title, desc, cat, pr, mood, d, pair);
                    TaskManager.addTask(t);
                    break;

                case 2:
                    TaskManager.listTasks();
                    break;

                case 3:
                    System.out.print("Task number to complete: ");
                    int comp = sc.nextInt();
                    TaskManager.completeTask(comp - 1);
                    break;

                case 4:
                    System.out.print("Task number to delete: ");
                    int del = sc.nextInt();
                    TaskManager.deleteTask(del - 1);
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    return;
            }
        }
    }
}
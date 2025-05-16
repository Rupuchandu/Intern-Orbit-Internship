import java.io.*;
import java.time.LocalDate;
import java.util.*;

class Task {
    String description;
    String category;
    LocalDate dueDate;
    boolean completed;

    public Task(String description, String category, LocalDate dueDate, boolean completed) {
        this.description = description;
        this.category = category;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public String toFileString() {
        return description + ";" + category + ";" + dueDate + ";" + completed;
    }

    public static Task fromFileString(String line) {
        String[] parts = line.split(";");
        return new Task(parts[0], parts[1], LocalDate.parse(parts[2]), Boolean.parseBoolean(parts[3]));
    }

    @Override
    public String toString() {
        return (completed ? "[✔]" : "[ ]") + " " + description + " | Category: " + category + " | Due: " + dueDate;
    }
}

public class ToDoListApp {
    static final String FILE_NAME = "tasks.txt";
    static List<Task> tasks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadTasks();

        while (true) {
            System.out.println("\n--- TO-DO LIST MENU ---");
            System.out.println("1. View Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Mark Task as Complete");
            System.out.println("4. Remove Task");
            System.out.println("5. Save & Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> listTasks();
                case 2 -> addTask();
                case 3 -> completeTask();
                case 4 -> removeTask();
                case 5 -> {
                    saveTasks();
                    System.out.println("Tasks saved. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Task.fromFileString(line));
            }
        } catch (IOException e) {
            // No file yet, start with an empty list
        }
    }

    static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks.");
            return;
        }

        System.out.println("\nTasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    static void addTask() {
        System.out.print("Enter task description: ");
        String desc = scanner.nextLine();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Enter due date (YYYY-MM-DD): ");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());

        tasks.add(new Task(desc, category, dueDate, false));
        System.out.println("Task added.");
    }

    static void completeTask() {
        listTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to mark as complete: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).completed = true;
            System.out.println("Task marked as complete.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    static void removeTask() {
        listTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to remove: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task removed.");
        } else {
            System.out.println("Invalid task number.");
        }
    }
}

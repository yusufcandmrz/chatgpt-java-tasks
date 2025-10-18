package yusufcandmrz.tasktracker;

import java.util.*;

public class TaskTracker {

    private List<Task> taskList;

    public TaskTracker() {
        taskList = new ArrayList<>();
    }

    public void run() {
        String processList = """
                1. Add Task
                2. List All Tasks
                3. Mark Task Complete
                4. Delete Task
                5. List by Priority
                6. Save to File
                7. Load from File
                8. Exit""";
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println(processList);
                System.out.print("Select a process: ");
                String processNumber = scanner.nextLine();
                switch (processNumber) {
                    case "1" -> addTask(scanner);
                    case "2" -> listAllTasks();
                    case "3" -> markTaskComplete(scanner);
                    case "4" -> deleteTask(scanner);
                    case "5" -> listByPriority();
                    case "6" -> saveToFile(scanner);
                    case "7" -> loadFromFile(scanner);
                    case "8" -> {
                        System.out.println("Exiting the program...");
                        return;
                    }
                    default -> System.out.println("Wrong process number");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addTask(Scanner scanner) {
        System.out.print("Enter your task's title: ");
        String title = scanner.nextLine();
        System.out.print("Enter your task's priority: ");
        String priority = scanner.nextLine();
        if (isValidPriority(priority)) {
            taskList.add(new Task(title, Priority.valueOf(priority.toUpperCase())));
            System.out.println("Task added to your list");
        } else {
            System.out.println("Task did not added to your list");
        }
    }

    private void listAllTasks() {
        taskList.forEach(System.out::println);
    }

    private void markTaskComplete(Scanner scanner) {
        System.out.print("Enter your task's id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        taskList.stream().filter(task -> task.getId() == id).findFirst().ifPresent(task -> {
            task.setCompleted(true);
            System.out.println("Task completed");
        });
    }

    private void deleteTask(Scanner scanner) {
        System.out.print("Enter you task's id: ");
        int id = scanner.nextInt();
        taskList.stream().filter(task -> task.getId() == id).findFirst().ifPresent(task -> {
            taskList.remove(task);
            System.out.println("Task deleted");
        });
    }

    private void listByPriority() {
        taskList.sort(Comparator.comparing(Task::getPriority).reversed());
        System.out.println("Task list ordered by their priority");
        taskList.forEach(System.out::println);
    }

    private void saveToFile(Scanner scanner) {
    }

    private void loadFromFile(Scanner scanner) {
    }

    private boolean isValidPriority(String priority) {
        return Arrays.stream(Priority.values())
                .anyMatch(p -> p.name().equalsIgnoreCase(priority));
    }

}

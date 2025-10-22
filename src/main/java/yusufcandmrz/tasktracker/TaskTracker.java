package yusufcandmrz.tasktracker;

import java.io.*;
import java.util.*;

public class TaskTracker {

    private static final String FILE_PATH = "tasks.ser";
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
                    case "6" -> saveToFile();
                    case "7" -> loadFromFile();
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
            System.out.println("Invalid priority. Please enter LOW, MEDIUM or HIGH.");
        }
    }

    private void listAllTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks to display.");
        } else {
            taskList.forEach(System.out::println);
        }
    }

    private void markTaskComplete(Scanner scanner) {
        System.out.print("Enter your task's id: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Optional<Task> taskOptional = taskList.stream().filter(task -> task.getId() == id).findFirst();
            if (taskOptional.isPresent()) {
                taskOptional.get().setCompleted(true);
                System.out.println("Task marked as complete.");
            } else {
                System.out.println("Task with id " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    private void deleteTask(Scanner scanner) {
        System.out.print("Enter you task's id: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            boolean removed = taskList.removeIf(task -> task.getId() == id);
            System.out.println(removed ? "Task deleted" : "Task with id " + id + " not found.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    private void listByPriority() {
        taskList.sort(Comparator.comparing(Task::getPriority).reversed());
        System.out.println("Task list ordered by their priority");
        taskList.forEach(System.out::println);
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(taskList);
            System.out.println("List of Task objects saved successfully to " + FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found at " + FILE_PATH);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No tasks file found. Starting with an empty list.");
            return;
        } else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                this.taskList = (List<Task>) ois.readObject();
                System.out.println("List of Task object read successfully!");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading tasks from file: " + e.getMessage());
            }
        }
    }

    private boolean isValidPriority(String priority) {
        return Arrays.stream(Priority.values())
                .anyMatch(p -> p.name().equalsIgnoreCase(priority));
    }

}

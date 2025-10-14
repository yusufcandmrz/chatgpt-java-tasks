package yusufcandmrz.tasktracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    }
}

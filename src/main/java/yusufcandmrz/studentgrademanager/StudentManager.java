package yusufcandmrz.studentgrademanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;


public class StudentManager {

    private final List<Student> studentList;

    public StudentManager() {
        studentList = new ArrayList<>();
    }

    public void run() {
        String operationList = """
                1. Add Student
                2. Add Grade
                3. Show All Students
                4. Calculate Average
                5. Find Top Student
                6. Search Student
                7. Exit""";
        int operationNumber;

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println(operationList);
                System.out.println("Select your operation:");
                operationNumber = scanner.nextInt();
                switch (operationNumber) {
                    case 1 -> handleAddStudent(scanner);
                    case 2 -> handleAddGrade(scanner);
                    case 3 -> showAllStudents();
                    case 4 -> handleCalculateAverage(scanner);
                    case 5 -> handleFindTopStudent();
                    case 6 -> handleSearchStudent(scanner);
                    case 7 -> {
                        System.out.println("Exit!");
                        return;
                    }
                    default -> System.out.println("Invalid operation number!");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleAddStudent(Scanner scanner) {
        int studentId = promptForStudentId(scanner);
        scanner.nextLine();
        System.out.println("Enter student name:");
        String studentName = scanner.nextLine();
        addStudent(studentId, studentName);
    }

    private void handleAddGrade(Scanner scanner) {
        int studentId = promptForStudentId(scanner);
        System.out.println("Enter student grade:");
        int studentGrade = scanner.nextInt();
        addGrade(studentId, studentGrade);
    }

    private void handleCalculateAverage(Scanner scanner) {
        int studentId = promptForStudentId(scanner);
        calculateAverage(studentId).ifPresentOrElse(
                average -> System.out.println("Average is " + average),
                () -> System.out.println("Student not found!")
        );
    }

    private void handleFindTopStudent() {
        findTopStudent().ifPresentOrElse(
                student -> System.out.println("Top student is " + student),
                () -> System.out.println("Student list is empty!")
        );
    }

    private void handleSearchStudent(Scanner scanner) {
        int studentId = promptForStudentId(scanner);
        searchStudent(studentId).ifPresentOrElse(student -> System.out.println("Student info: " + student),
                () -> System.out.println("Student not found!"));
    }

    private int  promptForStudentId(Scanner scanner) {
        System.out.println("Enter student id:");
        return scanner.nextInt();
    }

    private void addStudent(int id, String name) {
        if (searchStudent(id).isPresent()) {
            System.out.println("Id is already exists");
        } else {
            studentList.add(new Student(id, name));
            System.out.println("Student added successfully!");
        }
    }

    private void addGrade(int id, int grade) {
        searchStudent(id).ifPresentOrElse(
                student -> {
                    student.getGrades().add(grade);
                    System.out.println("Grade added successfully");
                },
                () -> System.out.println("Student not found!")
        );
    }

    private void showAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("Student list is empty!");
        } else {
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }

    private Optional<Double> calculateAverage(int id) {
        return searchStudent(id).map(student ->
                student.getGrades().stream()
                        .mapToInt(Integer::intValue)
                        .average()
                        .orElse(0.0)
        );

    }

    private Optional<Student> findTopStudent() {
        return studentList.stream()
                .max(Comparator.comparingDouble(student -> calculateAverage(student.getId()).orElse(0.0)));

    }

    private Optional<Student> searchStudent(int id) {
        return studentList.stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }
}

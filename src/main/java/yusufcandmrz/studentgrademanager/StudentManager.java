package yusufcandmrz.studentgrademanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class StudentManager {

    private final List<Student> studentList;

    public StudentManager() {
        studentList = new ArrayList<>();
        run();
    }

    private void run() {
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
                operationNumber = scanner.nextInt();
                switch (operationNumber) {
                    //TODO: complete switch structure
                    default:
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addStudent(int id, String name) {
        studentList.add(new Student(id, name));
        System.out.println("Student added successfully!");
    }

    private void addGradeToStudent(int id, int grade) {
        searchStudent(id).ifPresentOrElse(
                student -> {
                    student.getGrades().add(grade);
                    System.out.println("Grade added successfully");
                },
                () -> System.out.println("Student not found!")
        );
    }

    private void showAllStudents() {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    private double calculateAverage(int id) {
        Student student = searchStudent(id).orElseThrow(() -> new IllegalArgumentException("Student not found!"));
        return student.getGrades().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    private Student findTopStudent() {
        if (studentList.isEmpty()) {
            return null;
        }

        Student topStudent = studentList.get(0);
        double highestAverage = calculateAverage(topStudent.getId());

        for (int i = 1; i < studentList.size(); i++) {
            Student currentStudent = studentList.get(i);
            double currentStudentAverage = calculateAverage(currentStudent.getId());
            if (currentStudentAverage > highestAverage) {
                highestAverage = currentStudentAverage;
                topStudent = currentStudent;
            }
        }
        return topStudent;
    }

    private Optional<Student> searchStudent(int id) {
        return studentList.stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }
}

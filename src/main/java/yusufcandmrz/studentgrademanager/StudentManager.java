package yusufcandmrz.studentgrademanager;

import java.util.ArrayList;
import java.util.List;
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

        try(Scanner scanner = new Scanner(System.in)) {
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
        for (Student student : studentList) {
            if (student.getId() == id) {
                student.getGrades().add(grade);
                System.out.println("Grade added successfully");
            }
        }
    }

    private void showAllStudents() {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}

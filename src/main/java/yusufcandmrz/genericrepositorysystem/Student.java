package yusufcandmrz.genericrepositorysystem;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class Student implements Identifiable<Long> {
    private Long id;
    private String name;
    private int grade;
}

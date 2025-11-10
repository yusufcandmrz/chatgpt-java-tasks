package yusufcandmrz.genericrepositorysystem;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class Book implements Identifiable<Long> {
    private Long id;
    private String title;
    private String author;
}

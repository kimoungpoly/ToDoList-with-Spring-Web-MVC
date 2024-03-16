package co.cstad.todolist_web_mvc.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private Integer id;
    private String task;
    private String description;
    private boolean isDone;
    private LocalDateTime createdAt;

}

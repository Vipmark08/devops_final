package org.example.todo_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.todo_app.model.TaskStatus;

@Data
@AllArgsConstructor
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
}

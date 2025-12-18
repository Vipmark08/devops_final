package org.example.todo_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.todo_app.model.TaskStatus;

@Data
public class TaskRequest {

    @NotBlank(message = "Title must not be blank")
    @Size(max = 255, message = "Title must be at most 255 characters")
    private String title;

    @Size(max = 1000, message = "Description must be at most 1000 characters")
    private String description;

    private TaskStatus status = TaskStatus.NEW;
}

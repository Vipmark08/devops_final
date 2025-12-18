package org.example.todo_app.repository;

import org.example.todo_app.model.Task;
import org.example.todo_app.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
}

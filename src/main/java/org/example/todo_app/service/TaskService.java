package org.example.todo_app.service;

import lombok.RequiredArgsConstructor;
import org.example.todo_app.dto.TaskRequest;
import org.example.todo_app.dto.TaskResponse;
import org.example.todo_app.exception.TaskNotFoundException;
import org.example.todo_app.model.Task;
import org.example.todo_app.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return toResponse(task);
    }

    public TaskResponse createTask(TaskRequest request) {
        Task task = new Task(
                null,
                request.getTitle(),
                request.getDescription(),
                request.getStatus()
        );
        return toResponse(taskRepository.save(task));
    }

    public TaskResponse updateTask(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());

        return toResponse(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) throw new TaskNotFoundException(id);
        taskRepository.deleteById(id);
    }

    private TaskResponse toResponse(Task t) {
        return new TaskResponse(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getStatus()
        );
    }
}

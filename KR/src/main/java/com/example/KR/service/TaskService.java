package com.example.KR.service;

import com.example.KR.models.Project;
import com.example.KR.models.Task;
import com.example.KR.models.User;
import com.example.KR.models.enums.TaskStatus;
import com.example.KR.repositories.ProjectRepository;
import com.example.KR.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserService userService;

    public Task createTask(Task task, Long projectId, String username) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        User creator = userService.findByUsername(username);

        task.setProject(project);
        task.setCreatedBy(creator);
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId, User currentUser) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        if (!task.getCreatedBy().equals(currentUser)) {
            throw new AccessDeniedException("Only task creator can delete the task");
        }

        taskRepository.delete(task);
    }

    public List<Task> getProjectTasks(Project project) {
        return taskRepository.findByProject(project);
    }

    public void updateTaskStatus(Long taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        task.setStatus(status);
        taskRepository.save(task);
    }
}

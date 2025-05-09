package com.example.KR.controller;
import com.example.KR.models.Project;
import com.example.KR.models.Task;
import com.example.KR.models.User;
import com.example.KR.models.enums.ProjectRole;
import com.example.KR.models.enums.TaskStatus;
import com.example.KR.repositories.UserRepository;
import com.example.KR.service.ProjectService;
import com.example.KR.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final ProjectService projectService;
    private final UserRepository userRepository;

    @GetMapping("/projects/{projectId}/addTask")
    public String addTaskForm(@PathVariable Long projectId, Model model) {
        Project project = projectService.getProjectById(projectId);
        model.addAttribute("project", project);
        return "addTask";
    }

    @GetMapping("/projects/{projectId}/tasks")
    public String projectTasks(@PathVariable Long projectId, Model model) {
        Project project = projectService.getProjectById(projectId);
        model.addAttribute("project", project);
        model.addAttribute("tasks", taskService.getProjectTasks(project));
        model.addAttribute("ProjectRoleValues", ProjectRole.values());
        model.addAttribute("TaskStatusValues", TaskStatus.values());
        System.out.println("ProjectRoleValues added to model: " + Arrays.toString(ProjectRole.values()));
        return "project-task";
    }

    @PostMapping("/tasks/add")
    public String addTask(@ModelAttribute Task task,
                          @RequestParam Long projectId,
                          Principal principal) {
        taskService.createTask(task, projectId, principal.getName());
        return "redirect:/projects/" + projectId + "/tasks";
    }

    @PostMapping("/tasks/{taskId}/delete")
    public String deleteTask(
            @PathVariable Long taskId,
            @RequestParam Long projectId,
            Principal principal,
            RedirectAttributes redirectAttributes) {
        User currentUser = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        taskService.deleteTask(taskId, currentUser);

        redirectAttributes.addFlashAttribute("success", "Задача удалена");
        return "redirect:/projects/" + projectId + "/tasks";
    }

    @PostMapping("/tasks/{taskId}/status")
    public String updateTaskStatus(@PathVariable Long taskId,
                                   @RequestParam TaskStatus status,
                                   @RequestParam Long projectId) {
        taskService.updateTaskStatus(taskId, status);
        return "redirect:/projects/" + projectId + "/tasks";
    }
}

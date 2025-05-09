package com.example.KR.controller;

import com.example.KR.models.Project;
import com.example.KR.models.User;
import com.example.KR.models.enums.ProjectRole;
import com.example.KR.service.ProjectService;
import com.example.KR.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;

    @GetMapping("/addProject")
    public String addProject() {
        return "addProject";
    }

    @GetMapping("/project")
    public String projects(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("myProjects", projectService.getProjectsWhereMember(user));
        model.addAttribute("memberProjects", projectService.getProjectsWhereMember(user));
        model.addAttribute("ProjectRoleValues", ProjectRole.values());
        return "project";

    }

    @PostMapping("/project/add")
    public String addProject(@ModelAttribute Project project, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        projectService.createProject(project, user);
        return "redirect:/project";
    }

}

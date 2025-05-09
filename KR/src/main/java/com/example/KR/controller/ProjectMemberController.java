package com.example.KR.controller;
import com.example.KR.models.User;
import com.example.KR.models.enums.ProjectRole;
import com.example.KR.service.ProjectService;
import com.example.KR.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProjectMemberController {
    private final ProjectService projectService;
    private final UserService userService;

    @PostMapping("/projects/{projectId}/members")
    public String addMember(
            @PathVariable Long projectId,
            @RequestParam String userIdentifier,
            @RequestParam ProjectRole role,
            Principal principal,
            RedirectAttributes redirectAttributes) {

        try {
            User currentUser = userService.findByUsername(principal.getName());
            projectService.addMemberToProject(projectId, userIdentifier, role, currentUser);
            redirectAttributes.addFlashAttribute("success", "Пользователь успешно добавлен в проект");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/projects/" + projectId + "/settings";
    }
}
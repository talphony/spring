package com.example.KR.service;

import com.example.KR.models.Project;
import com.example.KR.models.ProjectMember;
import com.example.KR.models.User;
import com.example.KR.models.enums.ProjectRole;
import com.example.KR.repositories.ProjectMemberRepository;
import com.example.KR.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {
    private final ProjectMemberRepository projectMemberRepository;
    private final UserRepository userRepository;

    public void addMemberToProject(Project project, User user, ProjectRole role) {
        if (projectMemberRepository.existsByProjectAndUser(project, user)) {
            throw new IllegalStateException("Пользователь уже является участником проекта");
        }

        ProjectMember member = new ProjectMember();
        member.setProject(project);
        member.setUser(user);
        member.setRole(role);

        projectMemberRepository.save(member);
    }

    public List<User> getProjectMembers(Project project) {
        return projectMemberRepository.findByProject(project).stream()
                .map(ProjectMember::getUser)
                .collect(Collectors.toList());
    }

    public boolean isUserMemberOfProject(User user, Project project) {
        return projectMemberRepository.existsByProjectAndUser(project, user);
    }
}
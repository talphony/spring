package com.example.KR.models;

import java.io.Serializable;
import java.util.Objects;

public class ProjectMemberId implements Serializable {
    private Long project;
    private Long user;

    public ProjectMemberId() {
    }

    public ProjectMemberId(Long project, Long user) {
        this.project = project;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectMemberId that = (ProjectMemberId) o;
        return project.equals(that.project) && user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, user);
    }
}
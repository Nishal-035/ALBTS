package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.Project;

@Service
public class ProjectService {

    private List<Project> projects = new ArrayList<>();

    public Project addProject(Project project) {
        project.setProjectId(1L);
        projects.add(project);
        return project;
    }

    public List<Project> getAllProjects() {
        return projects;
    }

    public Project getProjectById(Long id) {
        return projects.get(0);
    }

    public Project updateProject(Long id, Project project) {
        project.setProjectId(id);
        projects.set(0, project);
        return project;
    }

    public List<Project> getProjectsByStatus(String status) {
    List<Project> result = new ArrayList<>();

    for (Project project : projects) {
        if (project.getStatus() != null &&
            project.getStatus().equalsIgnoreCase(status)) {
            result.add(project);
        }
    }
    return result;
}

}


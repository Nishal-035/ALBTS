package com.examly.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.Project;
import com.examly.springapp.repository.ProjectRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProjectService {

    private final ProjectRepo projectRepo;

    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    private static final Logger logger =
        LoggerFactory.getLogger(ProjectService.class);

    public Project addProject(Project project) {
        logger.info("Adding new project: {}", project.getProjectName());
        return projectRepo.save(project);
    }

    public List<Project> getAllProjects() {
        logger.info("Fetching all projects");
        return projectRepo.findAll();
    }

    // Get project by ID
    public Project getProjectById(Long id) {
        return projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    // Update project
    public Project updateProject(Long id, Project updatedProject) {
        Project existingProject = getProjectById(id);
        existingProject.setProjectName(updatedProject.getProjectName());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setStatus(updatedProject.getStatus());
        return projectRepo.save(existingProject);
    }

    // Get projects by status
    public List<Project> getProjectsByStatus(String status) {
        return projectRepo.findByStatus(status);
    }
}

package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/projects")
public class ProjectController {

    @RequestMapping("/{id}")
    public void getProjectById(@PathVariable Long id) {
    }
}

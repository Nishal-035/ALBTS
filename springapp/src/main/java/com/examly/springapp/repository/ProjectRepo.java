package com.examly.springapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.examly.springapp.model.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {

    List<Project> findByStatus(String status);
}

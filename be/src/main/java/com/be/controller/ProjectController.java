package com.be.controller;

import com.be.entity.Project;
import com.be.repository.ProjectRepository;
import com.be.repository.TaskRepository;
import com.be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @PostMapping("")
    public Project createProject(@RequestBody Project project) {
        Project projectResult = projectRepository.save(project);
        return projectResult;
    }

    @DeleteMapping("/{idProject}")
    public void deleteProjectById(@PathVariable int idProject) {
        Project projectResult = projectRepository.findProjectByIdProject(idProject);
        projectRepository.delete(projectResult);
    }

    @PatchMapping("")
    public Project updateProject(@RequestBody Project project) {
        Project projectResult=projectRepository.save(project);
        return projectResult;
    }
}

package com.fapi.controller;

import com.fapi.DTO.Project;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @GetMapping("")
    public List<Project> getAllProjects() {
        RestTemplate restTemplate = new RestTemplate();
        Project[] projectsResponse = restTemplate.getForObject(backendServerUrl + "/api/projects", Project[].class);
        return projectsResponse == null ? Collections.emptyList() : Arrays.asList(projectsResponse);
    }

    @PostMapping("")
    public Project createProject(@RequestBody Project task) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/projects", task, Project.class).getBody();
    }

    @DeleteMapping("/{idProject}")
    public void deleteProject(@PathVariable int idProject) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/projects/" + idProject);
    }

    @PatchMapping("")
    public Project updateProject(@RequestBody Project task) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.patchForObject(backendServerUrl + "/api/projects", task, Project.class);
    }

}

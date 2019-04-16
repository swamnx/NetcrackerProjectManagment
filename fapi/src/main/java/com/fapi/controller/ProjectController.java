package com.fapi.controller;

import com.fapi.DTO.Default.Project;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
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

    @GetMapping("/{idProject}")
    public ResponseEntity<com.fapi.DTO.ProjectMain.Project> getFullProjectById(@PathVariable int idProject) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<com.fapi.DTO.ProjectMain.Project> responseProjectDTO = restTemplate.getForEntity(backendServerUrl + "/api/projects/" + idProject,com.fapi.DTO.ProjectMain.Project.class);
            return responseProjectDTO;
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @PostMapping("/{idUser}")
    public ResponseEntity<com.fapi.DTO.ProjectMain.Project> createProject(@RequestBody Project project,@PathVariable int idUser){
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<com.fapi.DTO.ProjectMain.Project> responseProjectDTO = restTemplate.postForEntity(backendServerUrl + "/api/projects/"+idUser,project,com.fapi.DTO.ProjectMain.Project.class);
            return responseProjectDTO;
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @GetMapping("/users/{idUser}")
    public ResponseEntity<com.fapi.DTO.ProjectMain.Project[]> getAllProjects(@PathVariable int idUser){
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<com.fapi.DTO.ProjectMain.Project[]> responseProjectDTOs = restTemplate.getForEntity(backendServerUrl + "/api/projects/users/ "+idUser,com.fapi.DTO.ProjectMain.Project[].class);
            return responseProjectDTOs;
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
}

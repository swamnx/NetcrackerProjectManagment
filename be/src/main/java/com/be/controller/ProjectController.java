package com.be.controller;

import com.be.DTO.ProjectMain.ProjectMainMapper;
import com.be.entity.*;
import com.be.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{idProject}")
    public ResponseEntity<com.be.DTO.ProjectMain.Project> getProjectById(@PathVariable int idProject){
        Project projectResult = projectRepository.findProjectByIdProject(idProject);
        if(projectResult==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(ProjectMainMapper.INSTANCE.projectToProjectDTO(projectResult),HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<com.be.DTO.ProjectMain.Project> createProject(@RequestBody Project project){
        Project projectFound = projectRepository.findProjectByCode(project.getCode());
        if(projectFound!=null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Project projectResult = projectRepository.save(project);
        return new ResponseEntity<>(ProjectMainMapper.INSTANCE.projectToProjectDTO(projectResult),HttpStatus.OK);
    }
}

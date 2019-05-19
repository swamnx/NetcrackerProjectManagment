package com.be.controller;

import com.be.DTO.ProjectMain.ProjectMainMapper;
import com.be.entity.*;
import com.be.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

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
        ArrayList<User> users = new ArrayList<>();
        users.addAll(project.getProjectUsers());
        User user = userRepository.findUserByIdUser(users.get(0).getIdUser());
        Project projectResult = projectRepository.save(project);
        SortedSet<Project> userProjects = user.getUserProjects();
        userProjects.add(projectResult);
        user.setUserProjects(userProjects);
        userRepository.save(user);
        return new ResponseEntity<>(ProjectMainMapper.INSTANCE.projectToProjectDTO(projectRepository.findProjectByIdProject(projectResult.getIdProject())),HttpStatus.OK);
    }
    @GetMapping("/users/{idUser}")
    public ResponseEntity<Set<com.be.DTO.ProjectMain.Project>> getAllProjectsForUser(@PathVariable int idUser) {
        User userFound = userRepository.findUserByIdUser(idUser);
        if(userFound==null || userFound.getUserProjects().size()==0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ProjectMainMapper.INSTANCE.projectsToProjectsDTO(userFound.getUserProjects()),HttpStatus.OK);
    }

}

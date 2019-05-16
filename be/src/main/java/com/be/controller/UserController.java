package com.be.controller;

import com.be.DTO.ProjectMain.ProjectMainMapper;
import com.be.DTO.TaskMain.TaskMainMapper;
import com.be.DTO.UserMain.UserMainMapper;
import com.be.entity.Project;
import com.be.entity.User;
import com.be.repository.ProjectRepository;
import com.be.repository.TaskRepository;
import com.be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{idUser}")
    public ResponseEntity<com.be.DTO.UserMain.User> getUserById(@PathVariable int idUser){
        User userResult = userRepository.findUserByIdUser(idUser);
        if(userResult==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(UserMainMapper.INSTANCE.userToUserDTO(userResult),HttpStatus.OK);
    }

    @PostMapping("/{idUser}/projects/{idProject}")
    public ResponseEntity<com.be.DTO.UserMain.User> addUserOnProject(@PathVariable int idUser,@PathVariable int idProject) {
        User userResult = userRepository.findUserByIdUser(idUser);
        Project projectResult = projectRepository.findProjectByIdProject(idProject);
        if(userResult==null || projectResult==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Set<Project> projectSet = userResult.getUserProjects();
        if(!projectSet.add(projectResult)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userResult.setUserProjects(projectSet);
        userRepository.save(userResult);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{idUser}/projects/{idProject}")
    public ResponseEntity userOnProject(@PathVariable int idUser, @PathVariable int idProject){
        User userResult = userRepository.findUserByIdUser(idUser);
        Project projectResult = projectRepository.findProjectByIdProject(idProject);
        if(projectResult==null || userResult==null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        if(userResult.getUserProjects().contains(projectResult)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/emailPart")
    public ResponseEntity getUsersStartWithEmailForAddingOnProject(@RequestParam String emailPart){
        List<User> usersResult = userRepository.findFirst10ByEmailStartsWith(emailPart);
        return new ResponseEntity<>(ProjectMainMapper.INSTANCE.usersToUsersDTO(usersResult),HttpStatus.OK);
    }

}

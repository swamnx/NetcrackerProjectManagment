package com.be.controller;

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

    /*@PostMapping("")
    public ResponseEntity<com.be.DTO.UserMain.User> createUser(@RequestBody User user) {
        User userFound = userRepository.findUserByEmail(user.getEmail());
        if(userFound!=null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        User userResult = userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    /*@PostMapping("/sign")
    public ResponseEntity<com.be.DTO.UserMain.User> signUser(@RequestBody User user) {
        User userResult=userRepository.findUserByEmail(user.getEmail());
        if(userResult==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if(userResult.getPassword().equals(user.getPassword()))
            return new ResponseEntity<>(UserMainMapper.INSTANCE.userToUserDTO(userResult),HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    */
    @GetMapping("/{idUser}")
    public ResponseEntity<com.be.DTO.UserMain.User> getUserById(@PathVariable int idUser){
        User userResult = userRepository.findUserByIdUser(idUser);
        if(userResult==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(UserMainMapper.INSTANCE.userToUserDTO(userResult),HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<List<com.be.DTO.UserMain.User>> getAllUser(){
        List<User> usersResult = userRepository.findAll();
        return new ResponseEntity<>(UserMainMapper.INSTANCE.usersToUserDTOs(usersResult),HttpStatus.OK);
    }
    @PatchMapping("/addUserOnProject/{idProject}")
    public ResponseEntity<com.be.DTO.UserMain.User> addUserOnProject(@RequestBody com.be.DTO.UserMain.User user, @PathVariable int idProject) {
        User userResult = userRepository.findUserByIdUser(user.getIdUser());
        Project projectResult = projectRepository.findProjectByIdProject(idProject);
        if(userResult==null || projectResult==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Set<Project> projectSet = userResult.getUserProjects();
        if(!projectSet.add(projectResult)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userResult.setUserProjects(projectSet);
        User userResponse = userRepository.save(userResult);
        return new ResponseEntity<>(UserMainMapper.INSTANCE.userToUserDTO(userResponse),HttpStatus.OK);
    }

}

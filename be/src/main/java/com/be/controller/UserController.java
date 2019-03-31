package com.be.controller;

import com.be.entity.User;
import com.be.repository.ProjectRepository;
import com.be.repository.TaskRepository;
import com.be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("")
    public User createUser(@RequestBody User user) {
        User userResult = userRepository.save(user);
        return userResult;
    }

    @DeleteMapping("/{idUser}")
    public void deleteUserById(@PathVariable int idUser) {
        User userResult = userRepository.findUserByIdUser(idUser);
        userRepository.delete(userResult);
    }

    @PatchMapping("")
    public User updateUser(@RequestBody User user) {
        User userResult=userRepository.save(user);
        return userResult;
    }

    @GetMapping("/sign")
    public User signUser(@RequestParam String email, @RequestParam String password) {
        User userResult=userRepository.findUserByEmailAndPassword(email,password);
        return userResult;
    }

}

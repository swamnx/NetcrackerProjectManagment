package com.fapi.controller;

import com.fapi.DTO.User;
import com.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserControlller {
    @Autowired
    private UserService userService;
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @GetMapping("")
    public List<User> getAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        User[] usersResponse = restTemplate.getForObject(backendServerUrl + "/api/users", User[].class);
        return usersResponse == null ? Collections.emptyList() : Arrays.asList(usersResponse);
    }

    @PostMapping("")
    public User createUser(@RequestBody User user) {
        RestTemplate restTemplate = new RestTemplate();
        User respuser =restTemplate.postForEntity(backendServerUrl + "/api/users", user, User.class).getBody();
        return  respuser;
    }

    @DeleteMapping("/{idUser}")
    public void deleteUser(@PathVariable int idUser) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/users/" + idUser);
    }

    @PatchMapping("")
    public User updateUser(@RequestBody User user) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.patchForObject(backendServerUrl + "/api/users", user, User.class);
    }

    @GetMapping("/sign?email={email}&password={password}")
    public User signUser(@PathVariable String email, @PathVariable String password) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendServerUrl + "/api/users/sign?email=" + email + "&password=" + password, User.class).getBody();
    }
}

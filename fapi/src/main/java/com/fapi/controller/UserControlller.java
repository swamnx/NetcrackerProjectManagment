package com.fapi.controller;

import com.fapi.DTO.User;
import com.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<User> createUser(@RequestBody User user) {
        RestTemplate restTemplate = new RestTemplate();
        User responseUser =restTemplate.postForEntity(backendServerUrl + "/api/users", user, User.class).getBody();
        if(responseUser == null) return ResponseEntity.badRequest().build();
        return  ResponseEntity.ok(responseUser);
    }


    @GetMapping("/{idUser}")
    public ResponseEntity<User> getUserById(@PathVariable int idUser) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendServerUrl + "/api/users/" + idUser,User.class);
    }
    @DeleteMapping("/{idUser}")
    public void deleteUserById(@PathVariable int idUser) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/users/" + idUser);
    }

    @PatchMapping("")
    public User updateUser(@RequestBody User user) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.patchForObject(backendServerUrl + "/api/users", user, User.class);
    }

    @PostMapping("/sign")
    public ResponseEntity<User  > signUser(@RequestBody User user) {
        RestTemplate restTemplate = new RestTemplate();
        User responseUser = restTemplate.postForEntity(backendServerUrl + "/api/users/sign", user, User.class).getBody();
        if(responseUser == null) return ResponseEntity.notFound().build();
        return  ResponseEntity.ok(responseUser);
    }
}

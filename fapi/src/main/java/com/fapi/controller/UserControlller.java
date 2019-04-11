package com.fapi.controller;

import com.fapi.DTO.Default.User;
import com.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/users")
public class UserControlller {
    @Autowired
    private UserService userService;
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @PostMapping("/sign")
    public ResponseEntity<com.fapi.DTO.UserMain.User> signUser(@RequestBody User user) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<com.fapi.DTO.UserMain.User> responseUser = restTemplate.postForEntity(backendServerUrl + "/api/users/sign",user,com.fapi.DTO.UserMain.User.class);
            return responseUser;
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
        catch (HttpServerErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @PostMapping("")
    public ResponseEntity<com.fapi.DTO.UserMain.User> createUser(@RequestBody User user) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<com.fapi.DTO.UserMain.User> responseUser = restTemplate.postForEntity(backendServerUrl + "/api/users",user,com.fapi.DTO.UserMain.User.class);
            return responseUser;
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
        catch (HttpServerErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<com.fapi.DTO.UserMain.User> getUserById(@PathVariable int idUser) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<com.fapi.DTO.UserMain.User> responseUser = restTemplate.getForEntity(backendServerUrl + "/api/users/" + idUser,com.fapi.DTO.UserMain.User.class);
            return responseUser;
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
        catch (HttpServerErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
}

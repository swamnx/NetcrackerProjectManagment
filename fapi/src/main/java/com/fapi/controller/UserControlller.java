package com.fapi.controller;

import com.fapi.DTO.Default.User;
import com.fapi.service.UserServiceAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserControlller {
    @Value("${backend.server.url}")
    private String backendServerUrl;


    @GetMapping("/{idUser}")
    public ResponseEntity<com.fapi.DTO.UserMain.User> getUserById(@PathVariable int idUser) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForEntity(backendServerUrl + "/api/users/" + idUser,com.fapi.DTO.UserMain.User.class);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("/{idUser}/projects/{idProject}")
    public ResponseEntity isUserOnProject(@PathVariable int idUser, @PathVariable int idProject) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForEntity(backendServerUrl + "/api/users/"+idUser+"/projects/"+idProject,Void.class);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @PostMapping("/{idUser}/projects/{idProject}")
    public ResponseEntity addUserOnProject(@PathVariable int idUser, @PathVariable int idProject) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.postForEntity(backendServerUrl + "/api/users/"+idUser+"/projects/"+idProject,null,Void.class);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @GetMapping("/emailPart")
    public ResponseEntity<com.fapi.DTO.ProjectMain.User[]> getUsersStartWithEmailForAddingOnProject(@RequestParam String emailPart) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForEntity(backendServerUrl + "/api/users/emailPart?emailPart="+emailPart,com.fapi.DTO.ProjectMain.User[].class);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
}

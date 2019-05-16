package com.fapi.controller;

import com.fapi.DTO.Default.User;
import com.fapi.service.UserServiceAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
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
            ResponseEntity<com.fapi.DTO.UserMain.User> responseUser = restTemplate.getForEntity(backendServerUrl + "/api/users/" + idUser,com.fapi.DTO.UserMain.User.class);
            return responseUser;
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @GetMapping("")
    public ResponseEntity<com.fapi.DTO.UserMain.User[]> getAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<com.fapi.DTO.UserMain.User[]> responseUser = restTemplate.getForEntity(backendServerUrl + "/api/users",com.fapi.DTO.UserMain.User[].class);
            return responseUser;
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @PatchMapping("/addUserOnProject/{idProject}")
    public ResponseEntity<com.fapi.DTO.UserMain.User> addUserOnProject(@RequestBody User user, @PathVariable int idProject){
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(10000);
        httpRequestFactory.setReadTimeout(10000);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        try {
            com.fapi.DTO.UserMain.User responseUser = restTemplate.patchForObject(backendServerUrl + "/api/users/addUserOnProject/"+idProject,user, com.fapi.DTO.UserMain.User.class);
            return new ResponseEntity<>(responseUser,HttpStatus.OK);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @GetMapping("/{idUser}/projects/{idProject}")
    public ResponseEntity isUserOnProject(@PathVariable int idUser, @PathVariable int idProject) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity response = restTemplate.getForEntity(backendServerUrl + "/api/users/"+idUser+"/projects/"+idProject,Void.class);
            return response;
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @PostMapping("/{idUser}/projects/{idProject}")
    public ResponseEntity addUserOnProject(@PathVariable int idUser, @PathVariable int idProject) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity response = restTemplate.postForEntity(backendServerUrl + "/api/users/"+idUser+"/projects/"+idProject,null,Void.class);
            return response;
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @GetMapping("/emailPart")
    public ResponseEntity getUsersStartWithEmailForAddingOnProject(@RequestParam String emailPart) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity response = restTemplate.getForEntity(backendServerUrl + "/api/users/emailPart?emailPart="+emailPart,com.fapi.DTO.ProjectMain.User[].class);
            return response;
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
}

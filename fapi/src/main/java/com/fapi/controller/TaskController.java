package com.fapi.controller;

import com.fapi.DTO.CustomPageImpl;
import com.fapi.DTO.TaskMain.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Value("${backend.server.url}")
    private String backendServerUrl;


    @GetMapping("/onProjectForTaskAssign")
    public ResponseEntity<com.fapi.DTO.TaskMain.User[]> getUsersOnProjectForTaskAssign(@RequestParam String role,@RequestParam String firstEmailLetters, @RequestParam int idProject){
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForEntity(backendServerUrl +
                    "/api/tasks/onProjectForTaskAssign?role="+role+"&firstEmailLetters="+firstEmailLetters+"&idProject="+idProject,com.fapi.DTO.TaskMain.User[].class);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("/{idTask}")
    public ResponseEntity<com.fapi.DTO.TaskMain.Task> getTask(@PathVariable int idTask) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForEntity(backendServerUrl + "/api/tasks/"+idTask,com.fapi.DTO.TaskMain.Task.class);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @GetMapping("/page")
    public ResponseEntity<Page<com.fapi.DTO.TaskMain.TaskForTable>> getPageOfTasksForUser(@RequestParam Map<String,String> allParams,Principal user){
        RestTemplate restTemplate = new RestTemplate();
        try {
            allParams.put("email",user.getName());
            Page<com.fapi.DTO.TaskMain.TaskForTable> responsePage = restTemplate.getForObject(backendServerUrl + "/api/tasks/page?page={page}&size={size}&directionSort={directionSort}" +
                    "&fieldSort={fieldSort}&type={type}&search={search}&idProject={idProject}&email={email}",CustomPageImpl.class,allParams);
            return new ResponseEntity<>(responsePage,HttpStatus.OK);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @PostMapping("")
    public ResponseEntity createTask(@RequestBody com.fapi.DTO.TaskMain.Task task){
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.postForEntity(backendServerUrl+"/api/tasks",task,Task.class);
    }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }

    }
    @PreAuthorize("hasRole('ROLE_pm')")
    @PatchMapping("")
    public ResponseEntity<com.fapi.DTO.TaskMain.Task> updateTask(@RequestBody com.fapi.DTO.TaskMain.Task task){
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(10000);
        httpRequestFactory.setReadTimeout(10000);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        try {
            Task responseTask = restTemplate.patchForObject(backendServerUrl+"/api/tasks",task,com.fapi.DTO.TaskMain.Task.class);
            return new ResponseEntity<>(responseTask,HttpStatus.OK);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }

    }

}

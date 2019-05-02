package com.fapi.controller;

import com.fapi.DTO.CustomPageImpl;
import com.fapi.DTO.TaskMain.Task;
import com.fapi.DTO.TaskMain.TaskForProjectTable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

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
            ResponseEntity<com.fapi.DTO.TaskMain.User[]> responseUserDTOs = restTemplate.getForEntity(backendServerUrl
                    + "/api/tasks/onProjectForTaskAssign?role="+role+"&firstEmailLetters="+firstEmailLetters+"&idProject="+idProject,com.fapi.DTO.TaskMain.User[].class);
            return responseUserDTOs;
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("/{idTask}")
    public ResponseEntity<com.fapi.DTO.TaskMain.Task> getTask(@PathVariable int idTask) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<com.fapi.DTO.TaskMain.Task> responseTask = restTemplate.getForEntity(backendServerUrl + "/api/tasks/"+idTask,com.fapi.DTO.TaskMain.Task.class);
            return responseTask;
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @GetMapping("/page/availableTasks")
    public ResponseEntity<Page<com.fapi.DTO.TaskMain.TaskForTable>> getPageOfAvailableTasksForUser(@RequestParam Integer page,@RequestParam Integer size,Principal user){
        RestTemplate restTemplate = new RestTemplate();
        try {
            Page<com.fapi.DTO.TaskMain.TaskForTable> responsePage = restTemplate.getForObject(backendServerUrl + "/api/tasks/page/availableTasks?page="+page+"&email="+user.getName()+"&size="+size,CustomPageImpl.class);
            return new ResponseEntity<>(responsePage,HttpStatus.OK);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @GetMapping("/page/realTasks")
    public ResponseEntity<Page<com.fapi.DTO.TaskMain.TaskForTable>> getPageOfRealTasksForUser(@RequestParam Integer page,@RequestParam Integer size,Principal user){
        RestTemplate restTemplate = new RestTemplate();
        try {
            Page<com.fapi.DTO.TaskMain.TaskForTable> responsePage = restTemplate.getForObject(backendServerUrl + "/api/tasks/page/realTasks?page="+page+"&email="+user.getName()+"&size="+size,CustomPageImpl.class);
            return new ResponseEntity<>(responsePage,HttpStatus.OK);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @GetMapping("/page/projectTasks")
    public ResponseEntity<Page<TaskForProjectTable>> getPageOfTasksForProject(@RequestParam Integer page, @RequestParam Integer size, @RequestParam int idProject){
        RestTemplate restTemplate = new RestTemplate();
        try {
            Page<TaskForProjectTable> responsePage = restTemplate.getForObject(backendServerUrl + "/api/tasks/page/projectTasks?page="+page+"&size="+size+"&idProject="+idProject,CustomPageImpl.class);
            return new ResponseEntity<>(responsePage,HttpStatus.OK);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @PostMapping("")
    public ResponseEntity<com.fapi.DTO.TaskMain.Task> createTask(@RequestBody com.fapi.DTO.TaskMain.Task task){
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<com.fapi.DTO.TaskMain.Task> responseEntity = restTemplate.postForEntity(backendServerUrl+"/api/tasks",task,com.fapi.DTO.TaskMain.Task.class);
            return responseEntity;
    }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }

    }
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

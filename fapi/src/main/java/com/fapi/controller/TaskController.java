package com.fapi.controller;

import com.fapi.DTO.CustomPageImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Value("${backend.server.url}")
    private String backendServerUrl;

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
        catch (HttpServerErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @GetMapping("")
    public ResponseEntity<Page<com.fapi.DTO.TaskMain.Task>> getAllAvailableTasks(@RequestParam Integer page, @RequestParam Integer idUser){
        RestTemplate restTemplate = new RestTemplate();
        try {
            Page<com.fapi.DTO.TaskMain.Task> responsePage = restTemplate.getForObject(backendServerUrl + "/api/tasks?page="+page+"&idUser="+idUser,CustomPageImpl.class);
            return new ResponseEntity<>(responsePage,HttpStatus.OK);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
        catch (HttpServerErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

}

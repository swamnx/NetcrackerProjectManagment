package com.fapi.controller;

import com.fapi.DTO.Task;
import com.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @GetMapping("")
    public List<Task> getAllTasks() {
        RestTemplate restTemplate = new RestTemplate();
        Task[] tasksResponse = restTemplate.getForObject(backendServerUrl + "/api/tasks", Task[].class);
        return tasksResponse == null ? Collections.emptyList() : Arrays.asList(tasksResponse);
    }

    @PostMapping("")
    public Task createTask(@RequestBody Task task) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/tasks", task, Task.class).getBody();
    }

    @DeleteMapping("/{idTask}")
    public void deleteTask(@PathVariable int idTask) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/tasks/" + idTask);
    }

    @PatchMapping("")
    public Task updateTask(@RequestBody Task task) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.patchForObject(backendServerUrl + "/api/tasks", task, Task.class);
    }
}

package com.be.controller;

import com.be.entity.Task;
import com.be.repository.ProjectRepository;
import com.be.repository.TaskRepository;
import com.be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping("")
    public Task createTask(@RequestBody Task task) {
        Task taskResult = taskRepository.save(task);
        return taskResult;
    }

    @DeleteMapping("/{idTask}")
    public void deleteTaskById(@PathVariable int idTask) {
        Task taskResult = taskRepository.findTaskByIdTask(idTask);
        taskRepository.delete(taskResult);
    }

    @PatchMapping("")
    public Task updateTask(@RequestBody Task task) {
        Task taskResult=taskRepository.save(task);
        return taskResult;
    }
}

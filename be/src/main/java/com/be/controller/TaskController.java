package com.be.controller;

import com.be.DTO.TaskMain.TaskMainMapper;
import com.be.entity.*;
import com.be.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
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
    public Page<com.be.DTO.TaskMain.Task> getAllAvailableTasks(@RequestParam int page,@RequestParam int idUser) {
        User user = userRepository.findUserByIdUser(idUser);
        Page<Task> entityPage=taskRepository.findAllByTaskProjectIn(new PageRequest(page,10),user.getUserProjects());
        List<Task> tasks = entityPage.getContent();
        List<com.be.DTO.TaskMain.Task> tasksDTO = TaskMainMapper.INSTANCE.tasksToTasksDTO(tasks);

        return new PageImpl<>(tasksDTO,new PageRequest(page,10),entityPage.getTotalElements());
    }

}

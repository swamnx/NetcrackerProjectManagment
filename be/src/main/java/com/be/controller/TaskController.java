package com.be.controller;

import com.be.DTO.TaskMain.TaskMainMapper;
import com.be.entity.*;
import com.be.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    public Page<com.be.DTO.TaskMain.Task> getAllAvailableTasks(@RequestParam int page,@RequestParam int size,@RequestParam int idUser) {
        User user = userRepository.findUserByIdUser(idUser);
        Page<Task> entityPage=taskRepository.findAllByTaskProjectIn(new PageRequest(page,size),user.getUserProjects());
        List<Task> tasks = entityPage.getContent();
        List<com.be.DTO.TaskMain.Task> tasksDTO = TaskMainMapper.INSTANCE.tasksToTasksDTO(tasks);

        return new PageImpl<>(tasksDTO,new PageRequest(page,size),entityPage.getTotalElements());
    }
    @GetMapping("/{idTask}")
    public ResponseEntity<com.be.DTO.TaskMain.Task> getTaskById(@PathVariable int idTask){
        Task taskResult = taskRepository.findTaskByIdTask(idTask);
        if(taskResult==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(TaskMainMapper.INSTANCE.taskToTaskDTO(taskResult),HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<com.be.DTO.TaskMain.Task> createTask(@RequestBody Task task){
        Project project = projectRepository.findProjectByIdProject(task.getTaskProject().getIdProject());
        Set<Task> tasks = project.getProjectTasks();
        if(tasks.size()==0){
            task.setCode(0);
        }
        else{
            int lastcode=0;
            for (Task i:tasks) {
                if(i.getCode()>lastcode)
                    lastcode=i.getCode();
            }
            task.setCode(++lastcode);
        }
        task.setStatus("Open");
        task.setPriority("Normal");
        Task taskResult = taskRepository.save(task);
        if(taskResult==null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(TaskMainMapper.INSTANCE.taskToTaskDTO(taskResult),HttpStatus.OK);
    }

}
